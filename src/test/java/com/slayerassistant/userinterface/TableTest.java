package com.slayerassistant.userinterface;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void tableCannotBeConstructedWithNullHeaders()
    {
        exception.expect(NullPointerException.class);

        new Table(null, new Object[] {});
    }

    @Test
    public void tableCannotBeConstructedWithNullData()
    {
        exception.expect(NullPointerException.class);

        new Table(new String[]{ }, (Object[]) null);
    }

    @Test
    public void tableIsConstructedWithExpectedHeaders()
    {
        String[] headers = new String[] { "Fruit", "Meat" };
        Object[] data = new String[] {};

        Table table = new Table(headers, data);

        JTable component = (JTable) table.getTable().getViewport().getView();

        TableModel model = component.getModel();

        Object column1 = model.getColumnName(0);
        Object column2 = model.getColumnName(1);

        Assert.assertEquals(column1, "Fruit");
        Assert.assertEquals(column2, "Meat");
    }

    @Test
    public void tableIsConstructedWithExpectedData()
    {
        String[] headers = new String[] { "Fruit" };
        Object[] data = new String[] { "Apple", "Orange" };

        Table table = new Table(headers, data);

        JTable component = (JTable) table.getTable().getViewport().getView();

        TableModel model = component.getModel();

        Object row1 = model.getValueAt(0, 0);
        Object row2 = model.getValueAt(1, 0);

        Assert.assertEquals(row1, "Apple");
        Assert.assertEquals(row2, "Orange");
    }
}
