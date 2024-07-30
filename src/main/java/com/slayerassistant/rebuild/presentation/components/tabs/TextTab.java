package com.slayerassistant.rebuild.presentation.components.tabs;

import com.slayerassistant.rebuild.domain.Tab;

import javax.swing.*;
import java.awt.*;

public class TextTab extends JTextArea implements Tab<String[]> 
{
    public TextTab() 
    {
        setBackground(UIManager.getColor("TableHeader.background"));
        setMargin(new Insets(10, 5, 10, 5));
        setWrapStyleWord(true);
        setEnabled(false);
    }

    @Override
    public void update(String[] data) 
    {
        setText(String.join("\n\n", data));
    }

    @Override
    public void shutDown() {}
}