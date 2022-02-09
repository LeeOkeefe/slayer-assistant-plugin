package com.slayerassistant.domain;

import java.util.List;
import java.util.Objects;

public class SlayerTask
{
    public final String monster;
    public final int slayerLevel;
    public final String[] locations;
    public final Item[] itemsRequired;
    public final String[] attributes;
    public final String[] attackStyles;
    public final String[] alternatives;
    public final String[] slayerMasters;

    public SlayerTask(
            String monster,
            int slayerLevel,
            String[] locations,
            Item[] itemsRequired,
            String[] attributes,
            String[] attackStyles,
            String[] alternatives,
            String[] slayerMasters)
    {
        this.monster = Objects.requireNonNull(monster, "monster cannot be null");
        this.slayerLevel = slayerLevel;
        this.locations = Objects.requireNonNull(locations, "locations cannot be null");
        this.itemsRequired = Objects.requireNonNull(itemsRequired, "items required cannot be null");
        this.attributes = Objects.requireNonNull(attributes, "attributes cannot be null");
        this.attackStyles = Objects.requireNonNull(attackStyles, "attack styles cannot be null");
        this.alternatives = Objects.requireNonNull(alternatives, "alternatives cannot be null");
        this.slayerMasters = Objects.requireNonNull(slayerMasters, "slayer masters cannot be null");
    }
}
