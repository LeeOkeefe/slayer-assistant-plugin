package com.slayerassistant.services;

import com.slayerassistant.domain.Task;

import java.util.Comparator;

public interface TaskService 
{
    Task get(String name);

    Task[] getAll();
    
    Task[] getAll(Comparator<Task> comparator);

    Task[] searchPartialName(String text);
}
