package com.slayerassistant.userinterface;

import lombok.Getter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.IconTextField;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchBar
{
    @Getter
    private final IconTextField searchBar = new IconTextField();

    public SearchBar(Runnable onKeyTypedHandler, Runnable onClearHandler)
    {
        searchBar.addKeyListener(getKeyListener(onKeyTypedHandler));

        searchBar.addClearListener(() ->
        {
            onClearHandler.run();
            searchBar.setIcon(IconTextField.Icon.SEARCH);
            searchBar.setEditable(true);
        });

        setSearchBarStyle();
    }

    private KeyListener getKeyListener(Runnable onKeyTypedHandler)
    {
        return new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                onKeyTypedHandler.run();
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }

    private void setSearchBarStyle()
    {
        searchBar.setIcon(IconTextField.Icon.SEARCH);
        searchBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
        searchBar.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        searchBar.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        searchBar.setMinimumSize(new Dimension(0, 30));
    }
}
