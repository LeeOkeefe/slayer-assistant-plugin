package com.slayerassistant.data;

import com.google.gson.Gson;
import com.slayerassistant.bindings.ItemJsonBinding;
import com.slayerassistant.bindings.SlayerTaskJsonBinding;
import com.slayerassistant.domain.Item;
import com.slayerassistant.domain.SlayerTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class SlayerDataLoader implements DataLoader<Collection<SlayerTask>>
{
    private final String resourceFilePath;

    public SlayerDataLoader() throws FileNotFoundException
    {
        String fileName = "slayerTasks.json";

        URL url = getClass().getResource(fileName);

        if (url == null)
        {
            throw new FileNotFoundException(String.format("Could not find url for %s", fileName));
        }

        resourceFilePath = url.getPath();
    }

    @Override
    public Collection<SlayerTask> load()
    {
        FileReader fileReader;
        try
        {
            fileReader = new FileReader(resourceFilePath);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        SlayerTaskJsonBinding[] bindings = gson.fromJson(bufferedReader, SlayerTaskJsonBinding[].class);

        ArrayList<SlayerTask> slayerTasks = Arrays.stream(bindings)
                                                  .map(this::toDomain)
                                                  .collect(Collectors.toCollection(ArrayList::new));

        return Collections.unmodifiableCollection(slayerTasks);
    }

    private SlayerTask toDomain(SlayerTaskJsonBinding binding)
    {
        Item[] itemsRequired = Arrays.stream(binding.itemsRequired)
                .map(this::toDomain)
                .toArray(Item[]::new);

        return new SlayerTask(
                binding.monster,
                binding.slayerLevel,
                binding.locations,
                itemsRequired,
                binding.attributes,
                binding.attackStyles,
                binding.alternatives,
                binding.slayerMasters);
    }

    private Item toDomain(ItemJsonBinding binding)
    {
        return new Item(binding.name, binding.icon);
    }
}
