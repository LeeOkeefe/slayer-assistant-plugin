package com.slayerassistant;

import com.slayerassistant.data.SlayerDataLoader;
import com.slayerassistant.domain.SlayerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SlayerManager
{
    private Collection<SlayerTask> slayerTasks;

    public SlayerManager(SlayerDataLoader dataLoader)
    {
        try
        {
            slayerTasks = Objects.requireNonNull(dataLoader.load());
        }
        catch(NullPointerException e)
        {
            log.error("Failed to load slayer data", e);
        }
    }

    public Collection<SlayerTask> getAllSlayerTasks()
    {
        return slayerTasks.stream()
                          .sorted(Comparator.comparing(s -> s.monster))
                          .collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<SlayerTask> getSlayerTasksByPartialName(String monster)
    {
        return slayerTasks.stream()
                          .filter(slayerTask -> slayerTask.monster.toLowerCase().contains(monster.toLowerCase()))
                          .collect(Collectors.toCollection(ArrayList::new));
    }

    public SlayerTask getSlayerTaskByName(String monster)
    {
        return slayerTasks.stream()
                          .filter(slayerTask -> Objects.equals(slayerTask.monster.toLowerCase(), monster.toLowerCase()))
                          .findFirst()
                          .orElse(null);
    }
}
