package com.slayerassistant.rebuild.presentation.panels;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.presentation.components.Header;
import com.slayerassistant.rebuild.presentation.components.TaskTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskSelectedPanel extends JPanel 
{
    private final Header header = new Header();
    private final TaskTabs taskTabs = new TaskTabs();
    private final JButton closeButton = new JButton("Close");
    
    private final ActionListener onClickListener;

    public TaskSelectedPanel(Runnable onClose)
    {
        this.onClickListener = e -> onClose.run();
        closeButton.addActionListener(this.onClickListener);
        
        setLayout(new BorderLayout(0, 10));
        
        add(header, BorderLayout.NORTH);
        add(taskTabs, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
    
    public void shutDown()
    {
        taskTabs.shutDown();
        closeButton.removeActionListener(onClickListener);
    }
    
    public void update(Task task)
    {
        header.update(task.name, new ImageIcon(task.image));
        taskTabs.update(task);
    }
}

