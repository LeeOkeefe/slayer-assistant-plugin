package com.slayerassistant.userinterface;
import com.slayerassistant.rebuild.domain.WikiLink;

import net.runelite.client.util.LinkBrowser;

import lombok.Getter;

import javax.swing.*;

import java.awt.*;

public class ButtonUrlPanel {
    @Getter
    private final JPanel panel = new JPanel();

    public ButtonUrlPanel(WikiLink[] wikiLinks) {
        GridBagLayout gridLayout = new GridBagLayout();

        panel.setLayout(gridLayout);

        int row = 0;
        for (WikiLink wikiLink : wikiLinks) {
            GridBagConstraints c = new GridBagConstraints();

            c.gridy = row;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            c.insets = new Insets(2,2,2,2);

            panel.add(CreateButton(wikiLink.name, wikiLink.url), c);

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
