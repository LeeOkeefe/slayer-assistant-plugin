package com.slayerassistant.rebuild.presentation.components;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.function.Consumer;

public class SelectList<T> extends JList<T>
{
    private final ListSelectionListener onSelectListener;
    
    public SelectList(ListCellRenderer<T> renderer, Consumer<T> onSelect)
    {
        onSelectListener = e -> 
        {
            T selectedValue = getSelectedValue();
            if (e.getValueIsAdjusting() || selectedValue == null)
            {
                return;
            }
            onSelect.accept(selectedValue);
        };
        
        setCellRenderer(renderer);
        addListSelectionListener(onSelectListener);
    }
    
    public void shutDown() 
    {
        removeListSelectionListener(onSelectListener);
        setModel(new DefaultListModel<>());
    }
    
    public void update(T[] items)
    {
        SwingUtilities.invokeLater(() -> setListData(items));
    }
}
