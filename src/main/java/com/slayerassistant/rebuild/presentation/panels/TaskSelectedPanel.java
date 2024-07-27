package com.slayerassistant.rebuild.presentation.panels;

import com.slayerassistant.rebuild.domain.Icon;
import com.slayerassistant.rebuild.presentation.components.Header;
import net.runelite.client.ui.laf.RuneLiteTabbedPaneUI;

import javax.swing.*;
import java.awt.*;

public class TaskSelectedPanel extends JPanel 
{
    private final Header header = new Header();
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JButton closeButton = new JButton("Close");

    public TaskSelectedPanel()
    {
        setLayout(new BorderLayout());
        
        tabbedPane.setUI(new RuneLiteTabbedPaneUI()
        {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
            {
                return tabbedPane.getWidth() / tabbedPane.getTabCount();
            }
        });

        addTab(Icon.COMPASS.getIcon(), new JTextArea(), "Locations");
        addTab(Icon.INVENTORY.getIcon(), new JTextArea(), "Items required");
        addTab(Icon.COMBAT.getIcon(), new JTextArea(), "Combat");
        addTab(Icon.SLAYER_SKILL.getIcon(), new JTextArea(), "Slayer masters");
        addTab(Icon.WIKI.getIcon(), new JTextArea(), "Wiki");
        
        add(header, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    public void updateHeader(String title, ImageIcon image)
    {
        header.update(title, image);
    }

    private void addTab(ImageIcon icon, Component component, String tip) 
    {
        tabbedPane.addTab(null, icon, component, tip);
    }
}

