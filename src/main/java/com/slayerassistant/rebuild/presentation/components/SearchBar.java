package com.slayerassistant.rebuild.presentation.components;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.IconTextField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

public class SearchBar 
{
    private IconTextField searchBar = new IconTextField();

    private final DocumentListener onChangeListener;
    
    public SearchBar(Consumer<String> onChange)
    {
        onChangeListener = createDocumentListener(onChange);
        searchBar.getDocument().addDocumentListener(onChangeListener);
        
        initialiseStyles();
    }
    
    public Component getComponent()
    {
        return searchBar;
    }
    
    public void shutDown()
    {
        if (searchBar != null) 
        {
            searchBar.getDocument().removeDocumentListener(onChangeListener);
            searchBar = null;
        }
    }
    
    private DocumentListener createDocumentListener(Consumer<String> handler)
    {
        return new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e) { handler.accept(searchBar.getText()); }
            @Override
            public void removeUpdate(DocumentEvent e) { handler.accept(searchBar.getText()); }
            @Override
            public void changedUpdate(DocumentEvent e) { handler.accept(searchBar.getText()); }
        };
    }

    private void initialiseStyles()
    {
        searchBar.setIcon(IconTextField.Icon.SEARCH);
        searchBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH, 30));
        searchBar.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        searchBar.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        searchBar.setMinimumSize(new Dimension(0, 30));
    }
}
