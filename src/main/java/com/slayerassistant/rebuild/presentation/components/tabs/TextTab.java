package com.slayerassistant.rebuild.presentation.components.tabs;

import javax.swing.JTextArea;
import java.awt.*;

public class TextTab extends JTextArea implements Tab<String[]> 
{
    public TextTab() 
    {
        setMargin(new Insets(10, 5, 10, 5));
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