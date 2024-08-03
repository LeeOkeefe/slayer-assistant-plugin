package com.slayerassistant.rebuild.presentation.components.tabs;

import com.slayerassistant.rebuild.domain.Tab;
import com.slayerassistant.rebuild.domain.WikiLink;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.LinkBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class WikiTab extends JPanel implements Tab<WikiLink[]> 
{
    private final List<JButton> buttons = new ArrayList<>();

    public WikiTab() 
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(ColorScheme.DARKER_GRAY_COLOR, 1));
    }

    @Override
    public void update(WikiLink[] wikiLinks)
    {
        removeExistingButtons();

        add(Box.createVerticalStrut(5));
        
        for (WikiLink wikiLink : wikiLinks) 
        {
            JButton button = new JButton(wikiLink.name);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMaximumSize().height));
            button.addActionListener(e -> LinkBrowser.browse(wikiLink.url));
            buttons.add(button);
            
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
        for (JButton button : buttons)
        {
            Arrays.stream(button.getActionListeners())
                    .forEach(button::removeActionListener);
        }
        buttons.clear();
        removeAll();
    }
}