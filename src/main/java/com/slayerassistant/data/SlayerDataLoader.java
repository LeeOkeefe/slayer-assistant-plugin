package com.slayerassistant.data;

import com.google.gson.Gson;
import com.slayerassistant.domain.SlayerTask;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class SlayerDataLoader implements DataLoader<Collection<SlayerTask>>
{
    @Override
    public Collection<SlayerTask> load()
    {
        InputStream inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream("/data/slayerTasks.json"));
        try (Reader reader = new InputStreamReader(inputStream))
        {
            SlayerTask[] tasks = new Gson().fromJson(reader, SlayerTask[].class);

            return Arrays.asList(tasks);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
