package com.slayerassistant.domain;

import java.util.Objects;

public class Item
{
    public final String item;
    public final String icon;

    public Item(String item, String icon)
    {
        this.item = Objects.requireNonNull(item);
        this.icon = icon;
    }
}
