package com.slayerassistant.rebuild.presentation.panels;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.presentation.components.SearchBar;
import com.slayerassistant.rebuild.presentation.components.SelectList;
import com.slayerassistant.rebuild.services.TaskService;
import com.slayerassistant.userinterface.SlayerTaskRenderer;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;

public class MainPanel extends PluginPanel
{
    private final TaskService taskService;
    private final SearchBar searchBar;
    private final SelectList<Task> selectList;
    
    @Inject
    public MainPanel(TaskService taskService)
    {
        this.taskService = taskService;
        
        searchBar = new SearchBar(this::onSearchBarChanged);
        selectList = new SelectList<>(new SlayerTaskRenderer(), this::onTaskSelected);
        
        add(searchBar.getComponent());
        add(selectList.getComponent());
        
        selectList.update(taskService.getAll());
    }
    
    private void onSearchBarChanged(String searchTerm)
    {
        Task[] matchedTasks = searchTerm.isBlank()
            ? taskService.getAll()
            : taskService.searchPartialName(searchTerm.trim());
        
        selectList.update(matchedTasks);
    }
    
    private void onTaskSelected(Task task)
    {
        System.out.println(task.name);
    }
}
