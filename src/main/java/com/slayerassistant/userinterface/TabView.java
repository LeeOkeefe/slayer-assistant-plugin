package com.slayerassistant.userinterface;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TabView
{
    @Getter
    private final JTabbedPane tabbedPane = new JTabbedPane();

    public TabView()
    {
        tabbedPane.setPreferredSize(new Dimension(100, 200));
    }

    public void addTab(Tab tab)
    {
        Objects.requireNonNull(tab, "tab cannot be null");

        JLabel label = new JLabel();
        label.setIcon(tab.getIcon());

        Component component = tabbedPane.add(tab.getContent());
        int index = tabbedPane.indexOfComponent(component);

        tabbedPane.setTabComponentAt(index, label);
    }

    public void addTabs(Tab[] tabs)
    {
        for (Tab tab : tabs)
        {
            addTab(tab);
        }
    }
}
