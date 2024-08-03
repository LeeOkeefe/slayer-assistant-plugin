package com.slayerassistant.presentation.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JLabel
{
    public Header()
    {
        setFont(this.getFont().deriveFont(18f));
        setForeground(Color.ORANGE);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.TOP);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setIconTextGap(10);
    }
    
    public void update(String title, ImageIcon icon)
    {
        setText(title);
        setIcon(icon);
    }
}
