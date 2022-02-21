package com.slayerassistant.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

public class ImageUtils
{
    private static final String basePath = "/images/";

    /** Uses resources/images/ as the base path **/
    public static ImageIcon load(String path)
    {
        URL resource = ImageUtils.class.getResource(basePath + path);

        Objects.requireNonNull(resource, String.format("Resource at %s did not exist", basePath + path));

        return new ImageIcon(resource);
    }

    public static BufferedImage convertImageIconToBufferedImage(ImageIcon icon)
    {
        BufferedImage buffered = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = buffered.createGraphics();

        icon.paintIcon(null, graphics, 0,0);
        graphics.dispose();

        return buffered;
    }

    public static ImageIcon resize(ImageIcon imageIcon, int width, int height)
    {
        Image image = imageIcon.getImage();
        Image resized = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(resized);
    }
}
