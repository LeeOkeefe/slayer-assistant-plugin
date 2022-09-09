package com.slayerassistant.userinterface;

import com.slayerassistant.domain.WikiUrl;
import lombok.Getter;

import javax.swing.*;

public class UrlPanel {
    @Getter
    private final JPanel panel = new JPanel();

    public UrlPanel(WikiUrl[] wikiUrls) {

        for (WikiUrl wikiUrl: wikiUrls) {
            panel.add(CreateButton(wikiUrl.linkName, wikiUrl.linkUrl));
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
            System.out.println(e.getMessage());
        }
    }
}
