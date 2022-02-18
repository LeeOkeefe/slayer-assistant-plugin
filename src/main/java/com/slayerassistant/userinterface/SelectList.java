package com.slayerassistant.userinterface;

import com.slayerassistant.domain.SlayerTask;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                if (mouseEvent.getClickCount() == 1)
                {
                    int index = list.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0)
                    {
                        SlayerTask selected = (SlayerTask) list.getModel().getElementAt(index);
                        onClickHandler.accept(selected);
                    }
                }
            }
        };
    }
}
