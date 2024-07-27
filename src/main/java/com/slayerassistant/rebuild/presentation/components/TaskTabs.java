package com.slayerassistant.rebuild.presentation.components;

import com.slayerassistant.rebuild.domain.Icon;
import com.slayerassistant.rebuild.domain.WikiLink;
import com.slayerassistant.rebuild.presentation.components.tabs.Tab;
import com.slayerassistant.rebuild.presentation.components.tabs.TabKey;
import com.slayerassistant.rebuild.presentation.components.tabs.TextTab;
import com.slayerassistant.rebuild.presentation.components.tabs.WikiTab;
import net.runelite.client.ui.laf.RuneLiteTabbedPaneUI;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class TaskTabs extends JTabbedPane 
{
    private final Map<TabKey, Tab<?>> tabMap = new HashMap<>();

    public TaskTabs() {
        setUI(new RuneLiteTabbedPaneUI() 
        {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
            {
                return getWidth() / getTabCount();
            }
        });

        setTab(TabKey.LOCATIONS, Icon.COMPASS.getIcon(), new TextTab(), "Locations");
        setTab(TabKey.ITEMS_REQUIRED, Icon.INVENTORY.getIcon(), new TextTab(), "Items required");
        setTab(TabKey.COMBAT, Icon.COMBAT.getIcon(), new TextTab(), "Combat");
        setTab(TabKey.MASTERS, Icon.SLAYER_SKILL.getIcon(), new TextTab(), "Slayer masters");
        setTab(TabKey.WIKI, Icon.WIKI.getIcon(), new WikiTab(), "Wiki");
    }

    public void shutDown() 
    {
        tabMap.values().forEach(Tab::shutDown);
    }

    public void updateLocations(String[] locations) 
    {
        updateTab(TabKey.LOCATIONS, locations);
    }

    public void updateItemsRequired(String[] items) 
    {
        updateTab(TabKey.ITEMS_REQUIRED, items);
    }

    public void updateCombat(String[] attackStyles, String[] attributes) 
    {
        //updateTab(TabKey.COMBAT, new Object[][]{ attackStyles, attributes });
    }

    public void updateMasters(String[] masters) 
    {
        updateTab(TabKey.MASTERS, masters);
    }

    public void updateWikiLinks(WikiLink[] wikiLinks) 
    {
        updateTab(TabKey.WIKI, wikiLinks);
    }

    private <T> void updateTab(TabKey key, T data) 
    {
        Tab<?> tab = tabMap.get(key);
        if (tab == null) 
        {
            throw new IllegalArgumentException("No tab found for key: " + key);
        }

        try
        {
            Tab<T> typedTab = (Tab<T>) tab;
            typedTab.update(data);
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Invalid data type for tab: " + key, e);
        }
    }

    private void setTab(TabKey key, ImageIcon icon, Tab<?> tab, String tip) 
    {
        tabMap.put(key, tab);
        addTab(null, icon, (Component) tab, tip);
    }
}