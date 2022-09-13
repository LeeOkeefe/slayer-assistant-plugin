package com.slayerassistant.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SlayerTaskTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    // TODO: Find a way to pass constructor as a parameter instead of duplicating tests

    @Test
    public void cannotBeConstructedWithNullMonster()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};
        final Item[] items = new Item[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask(null, 123, strings, items, strings, strings, strings, strings, wikiUrls);
    }

    @Test
    public void cannotBeConstructedWithNullLocations()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};
        final Item[] items = new Item[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask("monster", 123, null, items, strings, strings, strings, strings, wikiUrls);
    }

    @Test
    public void cannotBeConstructedWithNullItemsRequired()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask("monster", 123, strings, null, strings, strings, strings, strings, wikiUrls);
    }

    @Test
    public void cannotBeConstructedWithNullAttributes()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};
        final Item[] items = new Item[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask("monster", 123, strings, items, null, strings, strings, strings, wikiUrls);
    }

    @Test
    public void cannotBeConstructedWithNullAttackStyles()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};
        final Item[] items = new Item[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask("monster", 123, strings, items, strings, null, strings, strings, wikiUrls);
    }

    @Test
    public void cannotBeConstructedWithNullAlternatives()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};
        final Item[] items = new Item[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask("monster", 123, strings, items, strings, strings, null, strings, wikiUrls);
    }

    @Test
    public void cannotBeConstructedWithNullSlayerMasters()
    {
        exception.expect(NullPointerException.class);

        final String[] strings = new String[]{};
        final Item[] items = new Item[]{};

        final WikiUrl[] wikiUrls = {new WikiUrl("", "")};
        new SlayerTask("monster", 123, strings, items, strings, strings, strings, null, wikiUrls);
    }

    @Test
    public void parametersAreAssignedToExpectedProperties()
    {
        final String expectedMonster = "Aberrant spectres";
        final int expectedSlayerLevel = 123;
        final String[] expectedLocations = new String[] { "Slayer Tower", "Stronghold Slayer Cave" };
        final Item[] expectedItemsRequired = new Item[] { new Item("Nose peg", "Nose peg.png") };
        final String[] expectedAttributes = new String[] { "Undead" };
        final String[] expectedAttackStyles = new String[] { "Magic" };
        final String[] expectedAlternatives = new String[] { "Deviant spectre" };
        final String[] expectedSlayerMasters = new String[] { "Vannaka" };
        final WikiUrl[] expectedWikiLink = {
                new WikiUrl("Aberrant spectre", "https://oldschool.runescape.wiki/w/Aberrant_spectre"),
                new WikiUrl("Deviant spectre", "https://oldschool.runescape.wiki/w/Deviant_spectre")
        };
        SlayerTask slayerTask = new SlayerTask(
                expectedMonster,
                expectedSlayerLevel,
                expectedLocations,
                expectedItemsRequired,
                expectedAttributes,
                expectedAttackStyles,
                expectedAlternatives,
                expectedSlayerMasters,
                expectedWikiLink);

        assert slayerTask.monster.equals(expectedMonster);
        assert slayerTask.slayerLevel == expectedSlayerLevel;
        assert slayerTask.locations == expectedLocations;
        assert slayerTask.itemsRequired == expectedItemsRequired;
        assert slayerTask.attributes == expectedAttributes;
        assert slayerTask.attackStyles == expectedAttackStyles;
        assert slayerTask.alternatives == expectedAlternatives;
        assert slayerTask.slayerMasters == expectedSlayerMasters;
    }
}
