package com.slayerassistant.userinterface;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class TextArea
{
    @Getter
    private final JTextArea textArea = new JTextArea();

    public TextArea(String text)
    {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        textArea.setBorder(new CompoundBorder(bevelBorder, emptyBorder));
        textArea.setEnabled(false);
        textArea.append(text);
    }
}