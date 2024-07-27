package com.slayerassistant.rebuild.domain;

import com.slayerassistant.SlayerAssistantPlugin;
import net.runelite.client.util.ImageUtil;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.function.UnaryOperator;

public enum Icon
{
    COMBAT("/images/combat.png"),
    COMPASS("/images/compass.png"),
    INVENTORY("/images/inventory.png"),
    SLAYER_SKILL("/images/slayer_icon.png"),
    WIKI("/images/wiki.png"),
    ;

    private final String file;
    Icon(String file)
    {
        this.file = file;
    }
    
    public BufferedImage getImage()
    {
        return ImageUtil.loadImageResource(SlayerAssistantPlugin.class, file);
    }
    
    public ImageIcon getIcon()
    {
        return getIcon(UnaryOperator.identity());
    }
    
    public ImageIcon getIcon(@Nonnull UnaryOperator<BufferedImage> func)
    {
        BufferedImage img = func.apply(getImage());
        return new ImageIcon(img);
    }
}
