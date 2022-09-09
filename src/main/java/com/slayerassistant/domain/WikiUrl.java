package com.slayerassistant.domain;

import java.util.Objects;

public class WikiUrl {
    public final String linkName;
    public final String linkUrl;

    public WikiUrl(String linkName, String linkUrl) {
        this.linkName = Objects.requireNonNull(linkName, "wiki name cannot be null");
        this.linkUrl = Objects.requireNonNull(linkUrl, "wiki url cannot be null");
    }
}
