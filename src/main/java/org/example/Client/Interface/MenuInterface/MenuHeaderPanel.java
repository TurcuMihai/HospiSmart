package org.example.Client.Interface.MenuInterface;

import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.*;

/**
 * clasa responsabila cu construirea header-ului paginii de meniu
 */
public class MenuHeaderPanel extends JPanel {
    MainFrame mainFrame;
    JLabel titleLabel = new JLabel("Menu");

    /**
     * constructor pentru obiecte de tipul MenuHeaderPanel
     *
     * @param mainFrame
     */
    public MenuHeaderPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        init();
    }

    private void init() {
        this.setBackground(new Color(77, 210, 255));
        add(titleLabel);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, screenSize.height / 10);
    }
}
