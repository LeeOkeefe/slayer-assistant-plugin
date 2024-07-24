package com.slayerassistant.rebuild.services;

import com.slayerassistant.rebuild.domain.Task;

import java.awt.image.BufferedImage;
import java.util.Comparator;

public interface TaskService 
{
    Task get(String name);

    Task[] getAll();
    
    Task[] getAll(Comparator<Task> comparator);

    Task[] searchPartialName(String text);
}
