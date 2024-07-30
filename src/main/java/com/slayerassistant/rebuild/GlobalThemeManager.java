package com.slayerassistant.rebuild;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class GlobalThemeManager
{
    public static void initialize() 
    {
        setFonts();
        setForegrounds();
    }
    
    private static void setFonts()
    {
        Font defaultFont = new Font("Arial", Font.PLAIN, 14);
        
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
        Color defaultFontColor = UIManager.getColor("TextArea.foreground");
        
        UIManager.put("Table.foreground", new ColorUIResource(defaultFontColor));
        UIManager.put("TextArea.foreground", new ColorUIResource(defaultFontColor));
        UIManager.put("TextField.foreground", new ColorUIResource(defaultFontColor));
        UIManager.put("Button.foreground", new ColorUIResource(defaultFontColor));
        UIManager.put("List.foreground", new ColorUIResource(defaultFontColor));
        UIManager.put("Label.foreground", new ColorUIResource(defaultFontColor));
    }
}