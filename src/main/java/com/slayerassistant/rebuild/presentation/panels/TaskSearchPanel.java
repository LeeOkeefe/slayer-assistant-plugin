package com.slayerassistant.rebuild.presentation.panels;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.presentation.components.SearchBar;
import com.slayerassistant.rebuild.presentation.components.SelectList;
import com.slayerassistant.userinterface.SlayerTaskRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class TaskSearchPanel extends JPanel
{
    private final SearchBar searchBar;
    private final SelectList<Task> selectList;
    
    public TaskSearchPanel(Consumer<String> onSearch, Consumer<Task> onSelect)
    {
        searchBar = new SearchBar(onSearch);
        selectList = new SelectList<>(new SlayerTaskRenderer(), onSelect);

        setLayout(new BorderLayout());
        
        add(searchBar, BorderLayout.NORTH);
        add(selectList, BorderLayout.CENTER);
    }
    
    public void updateTaskList(Task[] tasks)
    {
        selectList.update(tasks);
    }
}
