package com.slayerassistant.userinterface;

import com.slayerassistant.domain.SlayerTask;

import javax.swing.*;
import java.awt.*;

public class SlayerTaskRenderer extends JLabel implements ListCellRenderer<SlayerTask>
{
    @Override
    public Component getListCellRendererComponent(
            JList<? extends SlayerTask> list,
            SlayerTask value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
    {
        setText(value.monster);
        setVerticalAlignment(CENTER);

        return this;
    }
}
