package com.slayerassistant;

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
	
	private SlayerPluginPanel slayerPanel;
	private NavigationButton navButton;

	@Override
	protected void startUp()
	{
		slayerPanel = injector.getInstance(SlayerPluginPanel.class);

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
