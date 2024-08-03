package com.slayerassistant.rebuild.presentation;

import net.runelite.client.ui.ColorScheme;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GlobalThemeManager
{
    public static void initialize() 
    {
        setFonts();
        setForegrounds();
    }
    
    private static void setFonts()
    {
        Font defaultFont = createDefaultFont();
        
        UIManager.put("Table.font", new FontUIResource(defaultFont));
        UIManager.put("TextArea.font", new FontUIResource(defaultFont));
        UIManager.put("TextField.font", new FontUIResource(defaultFont));
        UIManager.put("Button.font", new FontUIResource(defaultFont));
        UIManager.put("List.font", new FontUIResource(defaultFont));
        UIManager.put("Label.font", new FontUIResource(defaultFont));
        UIManager.put("ToolTip.font", new FontUIResource(defaultFont.deriveFont(12f)));
    }
    
    private static void setForegrounds()
    {
        Color defaultColour = ColorScheme.LIGHT_GRAY_COLOR;
        
        UIManager.put("Table.foreground", new ColorUIResource(defaultColour));
        UIManager.put("TextArea.foreground", new ColorUIResource(defaultColour));
        UIManager.put("TextField.foreground", new ColorUIResource(defaultColour));
        UIManager.put("Button.foreground", new ColorUIResource(defaultColour));
        UIManager.put("List.foreground", new ColorUIResource(defaultColour));
        UIManager.put("Label.foreground", new ColorUIResource(defaultColour));
    }

    private static Font createDefaultFont()
    {
        try
        {
            InputStream is = GlobalThemeManager.class.getResourceAsStream("/fonts/SFPRODISPLAYREGULAR.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(Font.PLAIN, 14);
        }
        catch (IOException | FontFormatException e)
        {
            return new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        }
    }
}