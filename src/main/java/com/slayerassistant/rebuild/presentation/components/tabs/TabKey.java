package com.slayerassistant.rebuild.presentation.components.tabs;

public enum TabKey 
{
    LOCATIONS("Locations"),
    ITEMS_REQUIRED("Items required"),
    COMBAT("Combat"),
    MASTERS("Slayer masters"),
    WIKI("Wiki");
    
    private final String tooltip;
    TabKey(String tooltip)
    {
        this.tooltip = tooltip;
    }
    
    public String getTooltip()
    {
        return tooltip;
    }
}
