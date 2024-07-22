package com.slayerassistant.rebuild.domain;

import java.util.Objects;

public class WikiLink 
{
    public final String name;
    public final String url;

    public WikiLink(String name, String url) 
    {
        this.name = Objects.requireNonNull(name, "wiki name cannot be null");
        this.url = Objects.requireNonNull(url, "wiki url cannot be null");
    }
}
