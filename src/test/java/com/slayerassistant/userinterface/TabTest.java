package com.slayerassistant.userinterface;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.*;

public class TabTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void nullIconThrowsException()
    {
        exception.expect(NullPointerException.class);
        exception.expectMessage("icon cannot be null");

        new Tab(null, new JLabel());
    }

    @Test
    public void nullContentThrowsException()
    {
        exception.expect(NullPointerException.class);
        exception.expectMessage("content cannot be null");

        new Tab(new ImageIcon(), null);
    }
}
