package org.example.Client.Interface.LoginInterface;

import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * clasa responsabila cu construirea marginilor paginii de conectare
 */
public class MarginPanel extends JPanel {

    final MainFrame mainFrame;

    public MarginPanel(MainFrame frame) {
        this.mainFrame = frame;
        init();
    }

    private void init() {
        this.setBackground(Color.white);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width / 10, 6 * (screenSize.height / 8));
    }
}
