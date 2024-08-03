package com.slayerassistant.rebuild.presentation.panels;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.presentation.components.SearchBar;
import com.slayerassistant.rebuild.presentation.components.SelectList;
import com.slayerassistant.rebuild.presentation.SlayerTaskRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class TaskSearchPanel extends JPanel
{
    private final SearchBar searchBar;
    private final SelectList<Task> selectList;
    private final SlayerTaskRenderer taskRenderer = new SlayerTaskRenderer();
    
    public TaskSearchPanel(Consumer<String> onSearch, Consumer<Task> onSelect)
    {
        searchBar = new SearchBar(onSearch);
        selectList = new SelectList<>(taskRenderer, onSelect, this::onTaskHover);

        setLayout(new BorderLayout());
        
        add(searchBar, BorderLayout.NORTH);
        add(selectList, BorderLayout.CENTER);
    }
    
    public void shutDown()
    {
        searchBar.shutDown();
        selectList.shutDown();
    }
    
    public void updateTaskList(Task[] tasks)
    {
        selectList.update(tasks);
    }

    private void onTaskHover(int index)
    {
        taskRenderer.setHoverIndex(index);
        if (index != -1)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else
        {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
