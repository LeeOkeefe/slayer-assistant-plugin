package com.slayerassistant.rebuild.services;

import java.util.Comparator;

public interface TaskService 
{
    Task get(String name);

    Task[] getAll();
    
    Task[] getAll(Comparator<Task> comparator);

    Task[] searchPartialName(String text);
}
