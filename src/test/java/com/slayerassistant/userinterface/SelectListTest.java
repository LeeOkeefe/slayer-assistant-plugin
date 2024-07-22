package com.slayerassistant.userinterface;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class SelectListTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void cannotBeConstructedWithNullClickHandler()
    {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Click handler cannot be null");

        new SelectList<>(new DefaultListCellRenderer(), null);
    }

    @Test
    public void setOverwritesExistingSelectListWithExpectedItems()
    {
        SelectList<String> selectList = new SelectList(new DefaultListCellRenderer(), (s) -> {});

        String[] oldItems = new String[]{ "item1" };
        String[] newItems = new String[] { "item3", "item2" };

        selectList.set(oldItems);
        selectList.set(newItems);

        JList<String> actualItems = selectList.getItems();

        Assert.assertEquals(actualItems.getModel().getElementAt(0), newItems[0]);
        Assert.assertEquals(actualItems.getModel().getElementAt(1), newItems[1]);
    }
}
