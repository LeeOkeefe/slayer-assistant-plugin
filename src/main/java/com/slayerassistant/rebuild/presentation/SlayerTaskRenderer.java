package com.slayerassistant.rebuild.presentation;

import com.slayerassistant.rebuild.domain.Task;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class SlayerTaskRenderer extends JLabel implements ListCellRenderer<Task>
{
    private static int hoverIndex = -1;
    
    public void setHoverIndex(int index)
    {
        hoverIndex = index;
    }
    
    @Override
    public Component getListCellRendererComponent(
            JList<? extends Task> list,
            Task value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
    {
        setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH, 35));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        
        if (index == hoverIndex)
        {
            setBackground(ColorScheme.DARKER_GRAY_HOVER_COLOR);
            setForeground(Color.WHITE);
        }
        else
        {
            setBackground(ColorScheme.DARKER_GRAY_COLOR);
            setForeground(ColorScheme.LIGHT_GRAY_COLOR);
        }
        
        setOpaque(true);
        setText(value.name);

        return this;
    }
}
