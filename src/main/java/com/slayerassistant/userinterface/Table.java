package com.slayerassistant.userinterface;

import lombok.NonNull;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table
{
    private final String[] headers;
    private final Object[][] data;

    @Getter
    private final JScrollPane table;

    public Table(@NonNull String[] headers, @NonNull Object[]... data)
    {
        this.headers = headers;
        this.data = data;

        JTable table = createTable();
        this.table = createScrollPane(table);
    }

    private JScrollPane createScrollPane(Component table)
    {
        JScrollPane scrollPane = new JScrollPane(table);

        Color bg = new Color(35, 35, 35);

        scrollPane.setBackground(bg);
        scrollPane.getViewport().setBackground(bg);

        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border emptyBorder = BorderFactory.createEmptyBorder(8, 8, 8, 8);
        Border compoundBorder = BorderFactory.createCompoundBorder(bevelBorder, emptyBorder);

        scrollPane.setBorder(compoundBorder);

        return scrollPane;
    }

    private JTable createTable()
    {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        renderer.setForeground(Color.LIGHT_GRAY);

        JTable table = new JTable(new DefaultTableModel(headers, 0));

        for(String header : headers)
        {
            table.getColumn(header).setHeaderRenderer(renderer);
        }

        table.getTableHeader().setPreferredSize(new Dimension(0, 20));
        table.setGridColor(new Color(25, 25, 25));
        table.setBackground(Color.BLACK);
        table.setEnabled(false);
        table.setRowHeight(20);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        populateRows(model);

        return table;
    }

    private void populateRows(DefaultTableModel model)
    {
        int rows = getNumberOfRows(data);

        for(int i = 0; i < rows; i++)
        {
            Object[] data = new Object[rows];

            for(int j = 0; j < this.data.length; j++)
            {
                data[j] = i > this.data[j].length - 1 ? "" : this.data[j][i];
            }

            model.addRow(data);
        }
    }

    private int getNumberOfRows(Object[]... balls)
    {
        int rows = balls[0].length;

        for(Object[] dataset : balls)
        {
            if (dataset.length > rows)
            {
                rows = dataset.length;
            }
        }

        return rows;
    }
}
