package com.slayerassistant.presentation.panels;

import com.slayerassistant.domain.Task;
import com.slayerassistant.presentation.components.Header;
import com.slayerassistant.presentation.components.TaskTabs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setBorder(new EmptyBorder(5, 0, 0, 0));
        
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
        SwingUtilities.invokeLater(() -> taskTabs.update(task));
    }
}

