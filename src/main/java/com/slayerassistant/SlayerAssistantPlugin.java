package com.slayerassistant;

import com.google.inject.Binder;
import com.slayerassistant.rebuild.services.TaskService;
import com.slayerassistant.rebuild.services.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Slayer Assistant"
)
public class SlayerAssistantPlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;
	
	@Inject
	private SlayerPluginPanel slayerPanel;
	
	private NavigationButton navButton;

	public static final String TASKS_JSON_PATH = "/data/tasks.json";
	public static final String WIKI_URL = "https://oldschool.runescape.wiki/w/";

    @Override
	public void configure(Binder binder)
	{
        binder
			.bind(TaskService.class)
			.toInstance(new TaskServiceImpl(TASKS_JSON_PATH, WIKI_URL));
	}

	@Override
	protected void startUp()
	{
		injector.injectMembers(this);

		navButton = getNavButton();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);
	}

	private NavigationButton getNavButton()
	{
		BufferedImage bufferedImage = null;

		try
		{
			bufferedImage = ImageUtil.loadImageResource(getClass(), "/images/slayer_icon.png");
		}
		catch(NullPointerException e)
		{
			log.error(String.format("Could not find image resource at %s", getClass() + "/images/slayer_icon.png"), e);
		}

		return NavigationButton.builder()
				.tooltip("Slayer assistant")
				.icon(bufferedImage)
				.priority(10)
				.panel(slayerPanel)
				.build();
	}
}
