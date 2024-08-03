package com.slayerassistant.presentation.components.tabs;

import com.slayerassistant.domain.Tab;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;

public class TextTab extends JTextPane implements Tab<String[]>
{
    public TextTab()
    {
        setMargin(new Insets(10, 5, 10, 5));
        setEditable(false);
        setLineSpacing();
    }
    
    @Override
    public void update(String[] data)
    {
        resetParagraphs();
        
        if (data.length == 0)
        {
            addParagraph("None");
            return;
        }
        Arrays.stream(data).forEach(this::addParagraph);
    }

    @Override
    public void shutDown() { }
    
    private void addParagraph(String text)
    {
        StyledDocument doc = getStyledDocument();
        try
        {
            doc.insertString(doc.getLength(), text + "\n", null);
        }
        catch (BadLocationException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void resetParagraphs()
    {
        setText("");
    }

    private void setLineSpacing()
    {
        selectAll();
        MutableAttributeSet set = new SimpleAttributeSet(getParagraphAttributes());
        StyleConstants.setLineSpacing(set, 0.5f);
        setParagraphAttributes(set, true);
    }
}