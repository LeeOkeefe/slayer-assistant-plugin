package com.slayerassistant.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ItemTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void itemCannotBeConstructedWithNullName()
    {
        exception.expect(NullPointerException.class);
        exception.expectMessage("name cannot be null");

        new Item(null, "icon");
    }
}
