package com.slayerassistant.userinterface;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class ScrollableTextArea
{
    @Getter
    private final JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public ScrollableTextArea(String text)
    {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        textArea.setBorder(new CompoundBorder(bevelBorder, emptyBorder));
        textArea.setEnabled(false);
        textArea.setText(text);
        textArea.setCaretPosition(0);

        scrollPane.setViewportView(textArea);
    }
}

