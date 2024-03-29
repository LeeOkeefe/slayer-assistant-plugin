package com.slayerassistant.userinterface;
import com.slayerassistant.domain.WikiUrl;

import net.runelite.client.util.LinkBrowser;

import lombok.Getter;

import javax.swing.*;

import java.awt.*;

public class ButtonUrlPanel {
    @Getter
    private final JPanel panel = new JPanel();

    public ButtonUrlPanel(WikiUrl[] wikiUrls) {
        GridBagLayout gridLayout = new GridBagLayout();

        panel.setLayout(gridLayout);

        int row = 0;
        for (WikiUrl wikiUrl: wikiUrls) {
            GridBagConstraints c = new GridBagConstraints();

            c.gridy = row;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            c.insets = new Insets(2,2,2,2);

            panel.add(CreateButton(wikiUrl.linkName, wikiUrl.linkUrl), c);

            row++;
        }
    }

    private JButton CreateButton(String buttonText, String buttonUrl) {
        final JButton urlButton = new JButton();

        urlButton.setText(buttonText);
        urlButton.addActionListener(e -> LinkBrowser.browse(buttonUrl));

        return urlButton;
    }
}
