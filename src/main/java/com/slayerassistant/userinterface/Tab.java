package com.slayerassistant.userinterface;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Tab
{
    @Getter
    private final ImageIcon icon;

    @Getter
    private final Component content;

    public Tab(ImageIcon icon, Component content)
    {
        Objects.requireNonNull(icon, "icon cannot be null");
        Objects.requireNonNull(content, "content cannot be null");

        this.icon = icon;
        this.content = content;
    }
}
