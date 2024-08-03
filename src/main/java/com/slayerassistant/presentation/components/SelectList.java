package com.slayerassistant.presentation.components;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.function.Consumer;

public class SelectList<T> extends JList<T>
{
    public SelectList(ListCellRenderer<T> renderer, Consumer<T> onSelect)
    {
       ListSelectionListener onSelectListener = e ->
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
    
    public SelectList(ListCellRenderer<T> renderer, Consumer<T> onSelect, Consumer<Integer> onHoverHandler)
    {
        this(renderer, onSelect);
        
        MouseMotionListener onHoverListener = createOnHoverListener(onHoverHandler);
        addMouseMotionListener(onHoverListener);
    }

    public void shutDown() 
    {
        Arrays.stream(getListSelectionListeners())
                .forEach(this::removeListSelectionListener);
        
        Arrays.stream(getMouseMotionListeners())
                .forEach(this::removeMouseMotionListener);

        setModel(new DefaultListModel<>());
    }
    
    public void update(T[] items)
    {
        SwingUtilities.invokeLater(() -> setListData(items));
    }

    private MouseMotionListener createOnHoverListener(Consumer<Integer> onHoverHandler)
    {
        return new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e) { }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                int index = locationToIndex(e.getPoint());
                onHoverHandler.accept(index);
                repaint();
            }
        };
    }
}
