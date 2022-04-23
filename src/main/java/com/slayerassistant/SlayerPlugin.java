package com.slayerassistant;

import com.slayerassistant.utils.ImageUtils;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.image.BufferedImage;

@PluginDescriptor(
	name = "Slayer Assistant"
)
public class SlayerPlugin extends Plugin
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
		ImageIcon icon = ImageUtils.load("slayer_icon.png");
		BufferedImage bufferedImage = ImageUtils.convertImageIconToBufferedImage(icon);

		return NavigationButton.builder()
				.tooltip("Slayer assistant")
				.icon(bufferedImage)
				.priority(5)
				.panel(slayerPanel)
				.build();
	}
}
