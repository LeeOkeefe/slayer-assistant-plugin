package com.slayerassistant.rebuild.presentation.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JLabel
{
    public Header()
    {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        
        setFont(font);
        setForeground(Color.orange);
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
