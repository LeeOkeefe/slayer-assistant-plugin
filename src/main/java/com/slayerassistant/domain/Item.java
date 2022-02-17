package com.slayerassistant.domain;

import java.util.Objects;

public class Item
{
    public final String name;
    public final String icon;

    public Item(String name, String icon)
    {
        this.name = Objects.requireNonNull(name, "item name cannot be null");
        this.icon = icon;
    }
}
