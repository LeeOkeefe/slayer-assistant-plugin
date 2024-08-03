package com.slayerassistant;

import com.google.inject.Binder;
import com.slayerassistant.presentation.GlobalThemeManager;
import com.slayerassistant.domain.Icon;
import com.slayerassistant.presentation.panels.MainPanel;
import com.slayerassistant.services.TaskService;
import com.slayerassistant.services.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Slayer Assistant",
	description = "Assists with slayer task information",
	tags = { "slay", "slayer", "assistant" }
)
public class SlayerAssistantPlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;
	
	@Inject
	private MainPanel mainPanel;
	
	private NavigationButton navButton;

	private static final String TASKS_JSON_PATH = "/data/tasks.json";
	private static final String WIKI_URL = "https://oldschool.runescape.wiki/w/";
	private static final String BASE_IMAGES_PATH = "/images/monsters/";

    @Override
	public void configure(Binder binder)
	{
        binder
			.bind(TaskService.class)
			.toInstance(new TaskServiceImpl(TASKS_JSON_PATH, WIKI_URL, BASE_IMAGES_PATH));
	}

	@Override
	protected void startUp()
	{
		GlobalThemeManager.initialize();
		
		injector.injectMembers(this);

		navButton = getNavButton();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);
		mainPanel.shutDown();
	}

	private NavigationButton getNavButton()
	{
		return NavigationButton.builder()
				.tooltip("Slayer assistant")
				.icon(Icon.SLAYER_SKILL.getImage())
				.priority(10)
				.panel(mainPanel)
				.build();
	}
}
