package com.slayerassistant.userinterface;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.*;
import java.awt.*;

public class TabViewTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void addTabWhenTabIsNullThrowsException()
    {
        exception.expect(NullPointerException.class);
        exception.expectMessage("tab cannot be null");

        TabView tabView = new TabView();

        tabView.addTab(null);
    }

    @Test
    public void addTabPopulatesTabbedPaneWithTabContent()
    {
        TabView tabView = new TabView();
        JLabel expectedComponent = new JLabel("Tab 123");

        Tab tab = new Tab(new ImageIcon(), expectedComponent);
        tabView.addTab(tab);
        JTabbedPane tabbedPane = tabView.getTabbedPane();

        Component actualComponent = tabbedPane.getComponentAt(0);

        Assert.assertEquals(actualComponent, expectedComponent);
    }

    @Test
    public void addTabsPopulatesTabbedPaneWithTabContents()
    {
        TabView tabView = new TabView();
        Component expectedComponent1 = new JLabel("Component 1");
        Component expectedComponent2 = new JLabel("Component 1");

        Tab tab1 = new Tab(new ImageIcon(), expectedComponent1);
        Tab tab2 = new Tab(new ImageIcon(), expectedComponent2);

        JTabbedPane tabbedPane = tabView.getTabbedPane();

        tabView.addTabs(new Tab[] {tab1, tab2});

        Component actualComponent1 = tabbedPane.getComponentAt(0);
        Component actualComponent2 = tabbedPane.getComponentAt(1);

        Assert.assertEquals(actualComponent1, expectedComponent1);
        Assert.assertEquals(actualComponent2, expectedComponent2);
    }
}
