package com.slayerassistant.domain;

import lombok.Getter;

@Getter
public enum TabKey 
{
    LOCATIONS("Locations"),
    ITEMS_REQUIRED("Items"),
    COMBAT("Combat"),
    MASTERS("Masters"),
    WIKI("Wiki");
    
    private final String name;
    TabKey(String name)
    {
        this.name = name;
    }

}
