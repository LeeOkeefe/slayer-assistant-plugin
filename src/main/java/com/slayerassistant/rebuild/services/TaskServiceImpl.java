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
    private final String baseWikiUrl;
    
    public TaskServiceImpl(@NonNull String dataPath, @NonNull String baseWikiUrl)
    {
        this.baseWikiUrl = baseWikiUrl;
        
        InputStream inputStream = this.getClass().getResourceAsStream(dataPath);

        if (inputStream == null) 
        {
            throw new RuntimeException("Could not find JSON at path " + dataPath);
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
            log.error("JSON syntax error in file {}", dataPath, e);
        }
        catch (IOException e) 
        {
            log.error("Could not read JSON from file {}", dataPath, e);
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
        List<WikiLink> links = new ArrayList<>();
        
        links.add(createWikiLink(task.name));
        
        for (String variant : task.variants) 
        {
            links.add(createWikiLink(variant));
        }
        
        return links.toArray(new WikiLink[0]);
    }
    
    private WikiLink createWikiLink(String name)
    {
        String url = baseWikiUrl + name.replace(' ', '_');
        
        return new WikiLink(name, url);
    }
}
