package com.slayerassistant;

import com.slayerassistant.data.SlayerDataLoader;
import com.slayerassistant.domain.Item;
import com.slayerassistant.domain.SlayerTask;
import com.slayerassistant.domain.WikiUrl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SlayerManagerTest
{
    @Test
    public void getAllSlayerTasksReturnsExpectedSlayerTasks()
    {
        SlayerTask task1 = createSlayerTask("1");
        SlayerTask task2 = createSlayerTask("2");

        Collection<SlayerTask> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        SlayerDataLoader mockDataLoader = mock(SlayerDataLoader.class);
        when(mockDataLoader.load()).thenReturn(tasks);

        SlayerManager manager = new SlayerManager(mockDataLoader);

        Collection<SlayerTask> slayerTasks = manager.getAllSlayerTasks();

        Assert.assertEquals(slayerTasks.size(), 2);
        Assert.assertEquals(slayerTasks.toArray()[0], task1);
        Assert.assertEquals(slayerTasks.toArray()[1], task2);
    }

    @Test
    public void getAllSlayerTasksReturnsSlayerTasksOrderedAlphabetically()
    {
        SlayerTask zombie = createSlayerTask("Zombie");
        SlayerTask dragon = createSlayerTask("Dragon");
        SlayerTask bird = createSlayerTask("Bird");

        Collection<SlayerTask> slayerTasks = new ArrayList<>();
        slayerTasks.add(dragon);
        slayerTasks.add(bird);
        slayerTasks.add(zombie);

        SlayerDataLoader mockDataLoader = mock(SlayerDataLoader.class);
        when(mockDataLoader.load()).thenReturn(slayerTasks);

        SlayerManager manager = new SlayerManager(mockDataLoader);

        Collection<SlayerTask> tasks = manager.getAllSlayerTasks();

        Assert.assertEquals(tasks.toArray()[0], bird);
        Assert.assertEquals(tasks.toArray()[1], dragon);
        Assert.assertEquals(tasks.toArray()[2], zombie);
    }

    @Test
    public void getSlayerTaskByNameReturnsExpectedSlayerTask()
    {
        SlayerTask expectedSlayerTask = createSlayerTask("Cows");

        Collection<SlayerTask> slayerTasks = new ArrayList<>();
        slayerTasks.add(expectedSlayerTask);

        SlayerDataLoader mockDataLoader = mock(SlayerDataLoader.class);
        when(mockDataLoader.load()).thenReturn(slayerTasks);

        SlayerManager manager = new SlayerManager(mockDataLoader);
        SlayerTask slayerTask = manager.getSlayerTaskByName("Cows");

        assert slayerTask != null;
        Assert.assertEquals(slayerTask, expectedSlayerTask);
    }

    @Test
    public void getSlayerTaskByPartialNameReturnsExpectedSlayerTasks()
    {
        SlayerTask dragon = createSlayerTask("Dragon");
        SlayerTask dracula = createSlayerTask("Dracula");
        SlayerTask dog = createSlayerTask("Dog");

        Collection<SlayerTask> slayerTasks = new ArrayList<>();
        slayerTasks.add(dragon);
        slayerTasks.add(dracula);
        slayerTasks.add(dog);

        SlayerDataLoader mockDataLoader = mock(SlayerDataLoader.class);
        when(mockDataLoader.load()).thenReturn(slayerTasks);

        SlayerManager manager = new SlayerManager(mockDataLoader);
        Collection<SlayerTask> tasks = manager.getSlayerTasksByPartialName("Dra");

        Assert.assertEquals(tasks.size(), 2);
        Assert.assertEquals(tasks.toArray()[0], dragon);
        Assert.assertEquals(tasks.toArray()[1], dracula);
    }

    private SlayerTask createSlayerTask(String monster)
    {
        String[] strings = new String[] {};

        WikiUrl[] wikiUrls = {new WikiUrl("","")};
        return new SlayerTask(monster, 1, strings, new Item[]{}, strings, strings, strings, strings, wikiUrls);
    }
}
