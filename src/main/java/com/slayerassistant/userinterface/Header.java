package com.slayerassistant.userinterface;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Header
{
    @Getter
    JPanel header = new JPanel();

    public Header(Font font, String title, Color titleColor, ImageIcon imageIcon, int alignment)
    {
        JLabel text = createText(font, title, titleColor, alignment);
        JLabel image = createImage(imageIcon, alignment);

        GridBagConstraints gbc = new GridBagConstraints();
        header.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        header.add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 10;
        header.add(image, gbc);
    }

    public Header(Font font, String title, Color titleColor, int alignment)
    {
        JLabel text = createText(font, title, titleColor, alignment);

        header.add(text);
    }

    private JLabel createText(Font font, String text, Color color, int alignment)
    {
        JLabel label = new JLabel();

        label.setFont(font);
        label.setText(text);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);

        return label;
    }

    private JLabel createImage(ImageIcon imageIcon, int alignment)
    {
        JLabel label = new JLabel(imageIcon);
        label.setHorizontalAlignment(alignment);

        return label;
    }
}
