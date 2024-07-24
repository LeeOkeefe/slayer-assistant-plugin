package com.slayerassistant.rebuild.presentation.components;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.function.Consumer;

public class SelectList<T> 
{
    private JList<T> list = new JList<>();
    private final ListSelectionListener onSelectListener;
    
    public SelectList(ListCellRenderer<T> renderer, Consumer<T> onSelect)
    {
        onSelectListener = e -> 
        {
            T selectedValue = list.getSelectedValue();
            if (e.getValueIsAdjusting() || selectedValue == null)
            {
                return;
            }
            onSelect.accept(selectedValue);
        };
        
        list.setCellRenderer(renderer);
        list.addListSelectionListener(onSelectListener);
    }
    
    public void shutDown() 
    {
        list.removeListSelectionListener(onSelectListener);
        list = null;
    }
    
    public Component getComponent()
    {
        return list;
    }
    
    public void update(T[] items)
    {
        SwingUtilities.invokeLater(() -> list.setListData(items));
    }
}
