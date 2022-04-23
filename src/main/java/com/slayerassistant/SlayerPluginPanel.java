package com.slayerassistant;

import com.google.inject.Inject;
import com.slayerassistant.data.SlayerDataLoader;
import com.slayerassistant.domain.SlayerTask;
import com.slayerassistant.userinterface.TextArea;
import com.slayerassistant.userinterface.*;
import com.slayerassistant.utils.ImageUtils;
import net.runelite.client.ui.PluginPanel;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class SlayerPluginPanel extends PluginPanel
{
    private final SlayerManager slayerManager;
    private final SelectList<SlayerTask> slayerTasksList;
    private final SearchBar searchBar;

    private final String[] tabIcons = new String[] {"compass.png", "inventory.png", "combat.png", "slayer_icon.png"};
    private final String[] tableHeaders = new String[] {"Attack styles", "Attributes"};

    @Inject
    public SlayerPluginPanel() throws FileNotFoundException
    {
        slayerManager = new SlayerManager(new SlayerDataLoader());

        slayerTasksList = new SelectList<>(new SlayerTaskRenderer(), this::openTask);

        slayerTasksList.set(slayerManager.getAllSlayerTasks());

        searchBar = new SearchBar(handleOnKeyChanged(), handleOnClear());

        add(searchBar.getSearchBar());
        setTaskSelectList(slayerManager.getAllSlayerTasks());
    }

    public void setTaskSelectList(Collection<SlayerTask> tasks)
    {
        removeComponents(new Component[] { searchBar.getSearchBar() });

        slayerTasksList.set(tasks);

        JList<SlayerTask> items = slayerTasksList.getItems();

        JScrollPane scrollPane = new JScrollPane(items);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        scrollPane.setOpaque(false);
        add(scrollPane);

        revalidate();
        repaint();
    }

    private void openTask(SlayerTask task)
    {
        removeComponents(null);
        add(createHeader(task.monster));
        add(createTabView(task).getTabbedPane());
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e ->
        {
            closeTask();
            remove(closeButton);
        });
        add(closeButton);
    }

    private void closeTask()
    {
        removeComponents(null);
        add(searchBar.getSearchBar());
        setTaskSelectList(slayerManager.getAllSlayerTasks());
    }

    private JPanel createHeader(String monster)
    {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

        String fileName = String.format("monsters/%s", monster.replace(" ", "_").concat(".png"));

        ImageIcon imageIcon;
        try
        {
            ImageIcon img = ImageUtils.load(fileName);
            imageIcon = ImageUtils.resize(img, img.getIconWidth() / 2, img.getIconHeight() / 2);
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
            return new Header(font, monster, Color.ORANGE, SwingConstants.CENTER).getHeader();
        }

        return new Header(font, monster, Color.ORANGE, imageIcon, SwingConstants.CENTER).getHeader();
    }

    private TabView createTabView(SlayerTask task)
    {
        TabView tabView = new TabView();

        ImageIcon[] icons = Arrays.stream(tabIcons).map(ImageUtils::load).toArray(ImageIcon[]::new);

        String locations = convertStringsToLineSeparatedString(task.locations);
        String masters = convertStringsToLineSeparatedString(task.slayerMasters);
        String items = convertStringsToLineSeparatedString(Arrays.stream(task.itemsRequired)
                                           .map(i -> i.name)
                                           .toArray(String[]::new));

        Tab locationTab = new Tab(icons[0], new TextArea(locations).getTextArea());
        Tab itemTab = new Tab(icons[1], new TextArea(items).getTextArea());
        Tab combatTab = new Tab(icons[2], new Table(tableHeaders, task.attackStyles, task.attributes).getTable());
        Tab masterTab = new Tab(icons[3], new TextArea(masters).getTextArea());

        tabView.addTabs(new Tab[] { locationTab, itemTab, combatTab, masterTab });

        return tabView;
    }

    private String convertStringsToLineSeparatedString(String[] strings)
    {
        return String.join(System.lineSeparator(), strings);
    }

    private void removeComponents(@Nullable Component[] excludedComponents)
    {
        Component[] components = this.getComponents();

        for(Component component : components)
        {
            if (excludedComponents != null && Arrays.stream(excludedComponents).anyMatch(c -> c == component))
            {
                continue;
            }
            remove(component);
        }

        revalidate();
        repaint();
    }

    private Runnable handleOnKeyChanged()
    {
        return () ->
        {
            String text = searchBar.getSearchBar().getText();
            Collection<SlayerTask> tasks = Objects.equals(text, "") || Objects.equals(text, null)
                    ? slayerManager.getAllSlayerTasks()
                    : slayerManager.getSlayerTasksByPartialName(text);

            setTaskSelectList(tasks);
        };
    }

    private Runnable handleOnClear()
    {
        return () -> setTaskSelectList(slayerManager.getAllSlayerTasks());
    }
}