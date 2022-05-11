package com.slayerassistant.data;

import com.google.gson.Gson;
import com.slayerassistant.domain.SlayerTask;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Slf4j
public class SlayerDataLoader implements DataLoader<Collection<SlayerTask>>
{
    @Override
    public Collection<SlayerTask> load()
    {
        String jsonPath = "/data/slayerTasks.json";

        InputStream inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(jsonPath));
        try (Reader reader = new InputStreamReader(inputStream))
        {
            SlayerTask[] tasks = new Gson().fromJson(reader, SlayerTask[].class);

            return Arrays.asList(tasks);

        } catch (IOException e)
        {
            log.error(String.format("Could not read JSON from %s", this.getClass() + jsonPath), e);
        }

        return null;
    }
}
