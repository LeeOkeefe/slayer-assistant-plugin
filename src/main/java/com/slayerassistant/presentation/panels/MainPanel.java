package com.slayerassistant.presentation.panels;

import com.slayerassistant.domain.Panel;
import com.slayerassistant.domain.Task;
import com.slayerassistant.services.TaskService;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends PluginPanel
{
    private final TaskService taskService;

    private final TaskSearchPanel taskSearchPanel = new TaskSearchPanel(this::onSearchBarChanged, this::onTaskSelected);
    private final TaskSelectedPanel taskSelectedPanel = new TaskSelectedPanel(this::onTaskClosed);

    private final Map<Panel, JPanel> panels = new HashMap<>();
    private final JPanel currentPanelContainer = new JPanel(new BorderLayout());

    @Inject
    public MainPanel(TaskService taskService)
    {
        this.taskService = taskService;
        
        Task[] orderedTasks = taskService.getAll(Comparator.comparing(t -> t.name));
        taskSearchPanel.updateTaskList(orderedTasks);

        setLayout(new BorderLayout());

        panels.put(Panel.TASK_SEARCH, taskSearchPanel);
        panels.put(Panel.TASK_SELECTED, taskSelectedPanel);

        add(currentPanelContainer, BorderLayout.CENTER);
        showPanel(Panel.TASK_SEARCH);
    }

    public void shutDown()
    {
        taskSearchPanel.shutDown();
        taskSelectedPanel.shutDown();
    }

    private void onSearchBarChanged(String searchTerm)
    {
        Task[] matchedTasks = searchTerm.isBlank()
                ? taskService.getAll(Comparator.comparing(t -> t.name))
                : taskService.searchPartialName(searchTerm.trim());

        taskSearchPanel.updateTaskList(matchedTasks);
    }

    private void onTaskSelected(Task task)
    {
        taskSelectedPanel.update(task);
        showPanel(Panel.TASK_SELECTED);
    }

    private void onTaskClosed()
    {
        showPanel(Panel.TASK_SEARCH);
    }

    private void showPanel(Panel panel)
    {
        SwingUtilities.invokeLater(() -> 
        {
            currentPanelContainer.removeAll();
            currentPanelContainer.add(panels.get(panel), BorderLayout.CENTER);
            revalidate();
            repaint();
        });
    }
}
