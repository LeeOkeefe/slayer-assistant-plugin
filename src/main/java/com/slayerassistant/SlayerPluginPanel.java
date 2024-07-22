package com.slayerassistant;

import com.slayerassistant.rebuild.domain.Task;
import com.slayerassistant.rebuild.services.TaskService;
import com.slayerassistant.userinterface.ScrollableTextArea;
import com.slayerassistant.userinterface.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

@Slf4j
public class SlayerPluginPanel extends PluginPanel
{
    private final TaskService taskService;
    
    private final SelectList<Task> taskSelectList;
    private final SearchBar searchBar;

    private final String[] tabImageNamesWithExtensions = new String[] {"compass.png", "inventory.png", "combat.png", "slayer_icon.png", "wiki.png"};
    private final String[] tableHeaders = new String[] {"Attack styles", "Attributes"};

    @Inject
    public SlayerPluginPanel(TaskService taskService)
    {
        this.taskService = taskService;
        
        taskSelectList = new SelectList<>(new SlayerTaskRenderer(), this::openTask);

        //taskSelectList.set(taskService.getAll());

        searchBar = new SearchBar(handleOnKeyChanged(), handleOnClear());

        add(searchBar.getSearchBar());
        setTaskSelectList(taskService.getAll(Comparator.comparing(t -> t.name)));
    }

    public void setTaskSelectList(Task[] tasks)
    {
        removeComponents(new Component[] { searchBar.getSearchBar() });

        taskSelectList.set(tasks);

        JList<Task> items = taskSelectList.getItems();

        JScrollPane scrollPane = new JScrollPane(items);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        scrollPane.setOpaque(false);
        add(scrollPane);

        revalidate();
        repaint();
    }

    private void openTask(Task task)
    {
        removeComponents(null);
        add(createHeader(task.name));
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
        setTaskSelectList(taskService.getAll());
    }

    private JPanel createHeader(String monster)
    {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

        String monsterImageName = monster.replace(" ", "_").concat(".png").toLowerCase();
        String fileName = String.format("/images/monsters/%s", monsterImageName);

        ImageIcon imageIcon;
        try
        {
            BufferedImage img = ImageUtil.loadImageResource(getClass(), fileName);
            BufferedImage resizedImg = ImageUtil.resizeImage(img, img.getWidth() / 2, img.getHeight() / 2);

            imageIcon = new ImageIcon(resizedImg);
        }
        catch (NullPointerException e)
        {
            log.info(String.format("Could not find resource with name: %s", fileName), e);
            return new Header(font, monster, Color.ORANGE, SwingConstants.CENTER).getHeader();
        }

        return new Header(font, monster, Color.ORANGE, imageIcon, SwingConstants.CENTER).getHeader();
    }

    private TabView createTabView(Task task)
    {
        TabView tabView = new TabView();

        ArrayList<ImageIcon> icons = new ArrayList<>();
        for(String imageNameWithExtension : tabImageNamesWithExtensions)
        {
            BufferedImage image = ImageUtil.loadImageResource(getClass(), String.format("/images/%s", imageNameWithExtension));
            ImageIcon imageIcon = new ImageIcon(image);
            icons.add(imageIcon);
        }

        String locations = convertStringsToLineSeparatedString(task.locations);
        String masters = convertStringsToLineSeparatedString(task.masters);
        String items = convertStringsToLineSeparatedString(task.itemsRequired);

        Tab locationTab = new Tab(icons.get(0), new ScrollableTextArea(locations).getScrollPane());
        Tab itemTab = new Tab(icons.get(1), new ScrollableTextArea(items).getScrollPane());
        Tab combatTab = new Tab(icons.get(2), new Table(tableHeaders, task.attackStyles, task.attributes).getTable());
        Tab masterTab = new Tab(icons.get(3), new ScrollableTextArea(masters).getScrollPane());
        Tab wikiTab = new Tab(icons.get(4), new ButtonUrlPanel(task.wikiLinks).getPanel());

        tabView.addTabs(new Tab[] { locationTab, itemTab, combatTab, masterTab, wikiTab });

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
            Task[] tasks = Objects.equals(text, "") || Objects.equals(text, null)
                    ? taskService.getAll()
                    : taskService.searchPartialName(text);

            setTaskSelectList(tasks);
        };
    }

    private Runnable handleOnClear()
    {
        return () -> setTaskSelectList(taskService.getAll());
    }
}
