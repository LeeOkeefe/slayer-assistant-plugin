package com.slayerassistant;

import com.google.inject.Binder;
import com.slayerassistant.domain.Icon;
import com.slayerassistant.modules.TaskServiceModule;
import com.slayerassistant.presentation.panels.MainPanel;
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

    @Override
	public void configure(Binder binder)
	{
		binder.install(new TaskServiceModule());
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
		mainPanel.shutDown();
	}

	private NavigationButton getNavButton()
	{
		return NavigationButton.builder()
				.tooltip("Slayer Assistant")
				.icon(Icon.SLAYER_SKILL.getImage())
				.priority(10)
				.panel(mainPanel)
				.build();
	}
}
