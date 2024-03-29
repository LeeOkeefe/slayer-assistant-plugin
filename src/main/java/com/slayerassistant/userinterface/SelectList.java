package com.slayerassistant.userinterface;

import com.slayerassistant.domain.SlayerTask;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

public class SelectList<T>
{
    @Getter
    private final JList<T> items = new JList<>();

    public SelectList(ListCellRenderer<T> listCellRenderer, Consumer<SlayerTask> onClickHandler)
    {
        Objects.requireNonNull(onClickHandler, "Click handler cannot be null");

        items.setCellRenderer(listCellRenderer);
        items.addMouseListener(getMouseAdapter(onClickHandler));
        items.addMouseMotionListener(getMouseMotionListener());

        items.setOpaque(false);
    }

    public void set(Collection<T> items)
    {
        DefaultListModel<T> models = new DefaultListModel<>();

        for(T item : items)
        {
            models.addElement(item);
        }

        this.items.removeAll();
        this.items.setModel(models);
    }

    private MouseAdapter getMouseAdapter(Consumer<SlayerTask> onClickHandler)
    {
        return new MouseAdapter()
        {
            public void mouseClicked(MouseEvent mouseEvent)
            {
                JList list = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(mouseEvent))
                {
                    int index = list.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0)
                    {
                        SlayerTask selected = (SlayerTask) list.getModel().getElementAt(index);
                        onClickHandler.accept(selected);
                    }
                }
            }

            public void mouseExited(MouseEvent mouseEvent)
            {
                JList list = (JList) mouseEvent.getSource();

                list.clearSelection();
            }
        };
    }

    private MouseMotionListener getMouseMotionListener()
    {
        return new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e)
            {
                JList list = (JList) e.getSource();
                int index = list.locationToIndex(e.getPoint());
                SlayerTask task = (SlayerTask) list.getModel().getElementAt(index);
                list.setSelectedValue(task, false);
            }
        };
    }
}
