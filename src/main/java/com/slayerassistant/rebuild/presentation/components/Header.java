package com.slayerassistant.rebuild.presentation.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel
{
    private final JLabel label = new JLabel();
    
    public Header()
    {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        
        label.setFont(font);
        label.setForeground(Color.orange);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setIconTextGap(10);
    }
    
    public Component getComponent()
    {
        return label;
    }
    
    public void update(String title, ImageIcon icon)
    {
        label.setText(title);
        label.setIcon(icon);
    }
}
