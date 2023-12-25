package org.example.Client.Interface.LoginInterface;

import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * clasa responsabila cu construirea panel ului de jos a paginii de conectare
 */
public class BottomPanel extends JPanel {
    final MainFrame mainFrame;

    public BottomPanel(MainFrame frame) {
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
