package com.slayerassistant.rebuild.presentation.panels;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.services.TaskService;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;

public class MainPanel extends PluginPanel
{
    private final TaskService taskService;
    
    private final TaskSearchPanel taskSearchPanel = new TaskSearchPanel(this::onSearchBarChanged, this::onTaskSelected);
    private final TaskSelectedPanel taskSelectedPanel = new TaskSelectedPanel();
    
    @Inject
    public MainPanel(TaskService taskService)
    {
        this.taskService = taskService;
        
        add(taskSearchPanel);
        add(taskSelectedPanel);
        
        taskSearchPanel.updateTaskList(taskService.getAll());
    }
    
    private void onSearchBarChanged(String searchTerm)
    {
        Task[] matchedTasks = searchTerm.isBlank()
            ? taskService.getAll()
            : taskService.searchPartialName(searchTerm.trim());
        
        taskSearchPanel.updateTaskList(matchedTasks);
    }
    
    private void onTaskSelected(Task task)
    {
        // TODO: Hide TaskSearch on task selected
        // TODO: Update tab content to show selected task data
        //
        taskSelectedPanel.updateHeader(task.name, new ImageIcon(task.image));
    }
}
