package com.slayerassistant.rebuild.presentation.components;

import com.slayerassistant.rebuild.domain.Icon;
import com.slayerassistant.rebuild.domain.Tab;
import com.slayerassistant.rebuild.domain.TabKey;
import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.presentation.components.tabs.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.laf.RuneLiteTabbedPaneUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TaskTabs extends JTabbedPane 
{
    private final Map<TabKey, Tab<?>> tabMap = new HashMap<>();

    public TaskTabs() 
    {
        setUI(new RuneLiteTabbedPaneUI() 
        {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
            {
                return getWidth() / getTabCount();
            }
        });
        
        TabKey locations = TabKey.LOCATIONS;
        TabKey items = TabKey.ITEMS_REQUIRED;
        TabKey combat = TabKey.COMBAT;
        TabKey masters = TabKey.MASTERS;
        TabKey wiki = TabKey.WIKI;
        
        setTab(locations, Icon.COMPASS.getIcon(), new TextTab(), locations.getName());
        setTab(items, Icon.INVENTORY.getIcon(), new TextTab(), items.getName());
        setTab(combat, Icon.COMBAT.getIcon(), new TableTab(new String[] { "Attack Styles", "Attributes" }), combat.getName());
        setTab(masters, Icon.SLAYER_SKILL.getIcon(), new TextTab(), masters.getName());
        setTab(wiki, Icon.WIKI.getIcon(), new WikiTab(), wiki.getName());
    }

    public void shutDown() 
    {
        tabMap.values().forEach(Tab::shutDown);
    }
    
    public void update(Task task)
    {
        updateTab(TabKey.LOCATIONS, task.locations);
        updateTab(TabKey.ITEMS_REQUIRED, task.itemsRequired);
        updateTab(TabKey.COMBAT, new Object[][] { task.attackStyles, task.attributes });
        updateTab(TabKey.MASTERS, task.masters);
        updateTab(TabKey.WIKI, task.wikiLinks);
    }
    
    private <T> void updateTab(TabKey key, T data) 
    {
        Tab<?> rawTab = tabMap.get(key);

        if (rawTab == null) 
        {
            log.error("No tab found with key {}", key.toString());
            return;
        }

        @SuppressWarnings("unchecked")
        Tab<T> tab = (Tab<T>) rawTab;
        tab.update(data);
    }

    private void setTab(TabKey key, ImageIcon icon, Tab<?> tab, String tip) 
    {
        tabMap.put(key, tab);
        addTab(null, icon, (Component) tab, tip);
    }
}