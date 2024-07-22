package com.slayerassistant.userinterface;

import com.slayerassistant.rebuild.domain.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SlayerTaskRenderer extends JLabel implements ListCellRenderer<Task>
{
    @Override
    public Component getListCellRendererComponent(
            JList<? extends Task> list,
            Task value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
    {
        setBorder(new EmptyBorder(5, 5, 5, 5));

        setOpaque(isSelected);
        setBackground(isSelected ? Color.DARK_GRAY : Color.GRAY);
        setForeground(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
        setCursor(isSelected ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));

        setText(value.name);
        setVerticalAlignment(CENTER);

        return this;
    }
}
