package com.slayerassistant.userinterface;
import com.slayerassistant.domain.WikiUrl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

import java.awt.*;

@Slf4j
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
        urlButton.addActionListener(e -> openWebPage(buttonUrl));

        return urlButton;
    }

    private void openWebPage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            log.error("Failed to load slayer data", e);
        }
    }
}
