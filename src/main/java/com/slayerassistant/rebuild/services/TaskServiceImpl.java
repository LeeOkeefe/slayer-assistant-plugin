package com.slayerassistant.rebuild.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.domain.WikiLink;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
@Singleton
public class TaskServiceImpl implements TaskService 
{
    private final Map<String, Task> tasks = new HashMap<>();
    
    public TaskServiceImpl(@NonNull String jsonPath)
    {
        InputStream inputStream = this.getClass().getResourceAsStream(jsonPath);

        if (inputStream == null) 
        {
            throw new RuntimeException("Could not find JSON at path " + jsonPath);
        }
        
        try (Reader reader = new InputStreamReader(inputStream))
        {
            Type type = new TypeToken<Map<String, Task>>() {}.getType();
            Map<String, Task> data = new Gson().fromJson(reader, type);
            
            data.forEach((key, value) -> 
            {
                value.wikiLinks = createWikiLinks(value);
                tasks.put(key.toLowerCase(), value);
            });
        } 
        catch (JsonSyntaxException e) 
        {
            log.error("JSON syntax error in file {}", jsonPath, e);
        }
        catch (IOException e) 
        {
            log.error("Could not read JSON from file {}", jsonPath, e);
        }
    }
    
    @Override
    public Task get(String name)
    {
        return tasks.get(name.toLowerCase());
    }
    
    @Override
    public Task[] getAll()
    {
        return getAll(null);
    }
    
    @Override
    public Task[] getAll(Comparator<Task> comparator)
    {
        if (comparator == null) 
        {
            return tasks.values().toArray(new Task[0]);
        }
        
        return tasks.values().stream()
                .sorted(comparator)
                .toArray(Task[]::new);
    }
    
    @Override
    public Task[] searchPartialName(String text)
    {
        if (text == null || text.isEmpty()) 
        {
            return new Task[0];
        }
        
        String searchTerm = text.toLowerCase();
        return tasks
                .values()
                .stream()
                .filter(m -> m.name.toLowerCase().contains(searchTerm))
                .toArray(Task[]::new);
    }
    
    private WikiLink[] createWikiLinks(Task task)
    {
        String baseUrl = "https://oldschool.runescape.wiki/w/";
        List<WikiLink> urls = new ArrayList<>();
        
        urls.add(new WikiLink(task.name, baseUrl + task.name));
        for(String variant : task.variants) 
        {
            urls.add(new WikiLink(variant, baseUrl + variant));
        }
        
        return urls.toArray(new WikiLink[0]);
    }
}
