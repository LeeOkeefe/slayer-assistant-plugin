package com.slayerassistant.rebuild.presentation.components.tabs;

import com.slayerassistant.rebuild.domain.WikiLink;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.util.LinkBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WikiTab extends JPanel implements Tab<WikiLink[]> 
{
    private final Map<JButton, ActionListener> buttonListenerMap = new HashMap<>();

    public WikiTab() 
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void update(WikiLink[] data)
    {
        removeExistingButtons();

        add(Box.createVerticalStrut(5));
        
        for (WikiLink link : data) 
        {
            JButton button = new JButton(link.name);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            ActionListener listener = e -> LinkBrowser.browse(link.url);
            button.addActionListener(listener);
            buttonListenerMap.put(button, listener);
            
            add(Box.createVerticalStrut(5));
            add(button);
            add(Box.createVerticalStrut(5));
        }

        add(Box.createVerticalStrut(5));
    }

    @Override
    public void shutDown() 
    {
        removeExistingButtons();
    }

    private void removeExistingButtons() 
    {
        if (!buttonListenerMap.isEmpty()) 
        {
            for (Map.Entry<JButton, ActionListener> entry : buttonListenerMap.entrySet()) 
            {
                JButton button = entry.getKey();
                ActionListener listener = entry.getValue();
                button.removeActionListener(listener);
            }
            buttonListenerMap.clear();
        }
        removeAll();
    }
}