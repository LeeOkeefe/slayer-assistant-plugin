package com.slayerassistant.rebuild.presentation.components.tabs;

import com.slayerassistant.rebuild.domain.Tab;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableTab extends JScrollPane implements Tab<Object[][]>
{
    private final JTable table = new JTable();

    public TableTab(String[] columnNames)
    {
        setTableRenderer(columnNames);
        setHeaderRenderer();
        
        table.setRowHeight(25);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        
        setFocusable(false);
        setPreferredSize(new Dimension(table.getWidth(), 150));
        setViewportView(table);
    }

    @Override
    public void update(Object[][] data)
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        int maxRowCount = Math.max(data[0].length, data[1].length);
        for (int i = 0; i < maxRowCount; i++)
        {
            String attackStyle = i < data[0].length ? data[0][i].toString() : "";
            String attribute = i < data[1].length ? data[1][i].toString() : "";
            model.addRow(new Object[]{attackStyle, attribute});
        }
    }

    @Override
    public void shutDown()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    private void setTableRenderer(String[] columnNames)
    {
        table.setModel(new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
    }

    private void setHeaderRenderer()
    {
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Border border = BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.darkGray),
                        BorderFactory.createEmptyBorder(10, 3, 10, 3)
                );
                ((JComponent) c).setBorder(border);
                return c;
            }
        };

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++)
        {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
}