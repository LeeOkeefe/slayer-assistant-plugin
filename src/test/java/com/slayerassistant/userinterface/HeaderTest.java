package com.slayerassistant.userinterface;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class HeaderTest
{
    @Test
    public void ConstructorAddsLabelWithTextAndIconToPanel()
    {
        Font expectedFont = new Font(Font.SERIF, Font.PLAIN, 14);
        String expectedText = "Header";
        Color expectedForeground = Color.CYAN;
        ImageIcon expectedIcon = new ImageIcon("FileName");
        int expectedAlignment = CENTER;

        Header header = new Header(expectedFont, expectedText, expectedForeground, expectedIcon, expectedAlignment);

        Component[] headerComponents = header.getHeader().getComponents();

        Assert.assertEquals(headerComponents.length ,2);

        JLabel textComponent = (JLabel) headerComponents[0];
        JLabel iconComponent = (JLabel) headerComponents[1];

        Assert.assertEquals(textComponent.getFont(), expectedFont);
        Assert.assertEquals(textComponent.getText(), expectedText);
        Assert.assertEquals(textComponent.getForeground(), expectedForeground);
        Assert.assertEquals(textComponent.getHorizontalAlignment(), expectedAlignment);

        Assert.assertEquals(iconComponent.getIcon(), expectedIcon);
    }

    @Test
    public void ConstructorAddsLabelWithTextToPanel()
    {
        Font expectedFont = new Font(Font.SERIF, Font.PLAIN, 14);
        String expectedText = "Header";
        Color expectedForeground = Color.CYAN;
        int expectedAlignment = CENTER;

        Header header = new Header(expectedFont, expectedText, expectedForeground, expectedAlignment);

        Component[] headerComponents = header.getHeader().getComponents();

        JLabel component = (JLabel) headerComponents[0];

        Assert.assertEquals(headerComponents.length ,1);
        Assert.assertEquals(component.getFont(), expectedFont);
        Assert.assertEquals(component.getText(), expectedText);
        Assert.assertEquals(component.getForeground(), expectedForeground);
        Assert.assertEquals(component.getHorizontalAlignment(), expectedAlignment);
    }
}
