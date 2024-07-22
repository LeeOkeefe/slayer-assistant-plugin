package com.slayerassistant.rebuild.domain;

public class Task 
{
    public String name;
    public int levelRequired;
    public String[] itemsRequired;
    public String[] locations;
    public String[] attributes;
    public String[] attackStyles;
    public String[] variants;
    public String[] masters;
    public WikiLink[] wikiLinks;

    public Task(
            String name,
            int levelRequired,
            String[] itemsRequired,
            String[] locations,
            String[] attributes,
            String[] attackStyles,
            String[] variants,
            String[] masters,
            WikiLink[] wikiLinks) 
    {
        this.name = name;
        this.levelRequired = levelRequired;
        this.itemsRequired = itemsRequired;
        this.locations = locations;
        this.attributes = attributes;
        this.attackStyles = attackStyles;
        this.variants = variants;
        this.masters = masters;
        this.wikiLinks = wikiLinks;
    }
}
