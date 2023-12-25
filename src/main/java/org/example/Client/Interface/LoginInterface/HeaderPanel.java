package org.example.Client.Interface.LoginInterface;

import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * clasa responsabila cu construirea panel ului de sus a paginii de conectare
 */
public class HeaderPanel extends JPanel {

    final MainFrame mainFrame;
    Graphics2D graphics;
    BufferedImage image;

    public HeaderPanel(MainFrame frame) {
        this.mainFrame = frame;
        init();
    }

    private void init() {
        this.setBackground(new Color(77, 210, 255));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, screenSize.height / 8);
    }
}
