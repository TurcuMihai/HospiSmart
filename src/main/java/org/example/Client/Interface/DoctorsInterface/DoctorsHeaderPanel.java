package org.example.Client.Interface.DoctorsInterface;

import org.example.Client.Actions.BackAction;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * clasa responsabila de construirea header-ului paginii doctori
 */
public class DoctorsHeaderPanel extends JPanel {

    JLabel titleLabel = new JLabel("Doctori");

    JButton programButton = new JButton("Program");

    JButton leftArrowButton = new JButton("←");

    Dimension buttonSize = new Dimension(200, 30);

    BackAction backAction;

    Font buttonFont = new Font("Arial", Font.PLAIN, 35);

    /**
     * constructor pentru obiecte de tipul DoctorsHeaderPanel
     *
     * @param mainFrame
     */
    public DoctorsHeaderPanel(MainFrame mainFrame) {
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        programButton.setHorizontalAlignment(SwingConstants.RIGHT);
        programButton.setFont(buttonFont);
        leftArrowButton.setFont(new Font("Arial", Font.BOLD, 70));
        leftArrowButton.setBounds(50, 50, 300, 80);
        leftArrowButton.setFont(buttonFont);
        leftArrowButton.setFocusPainted(false);
        leftArrowButton.setBackground(new Color(0, 255, 255));
        leftArrowButton.setBorderPainted(false);
        leftArrowButton.setForeground(Color.WHITE);
        backAction = new BackAction(mainFrame);

        init();
    }

    /**
     * metoda responsabila de construirea panel-ului
     */
    private void init() {
        setLayout(new BorderLayout());
        this.setBackground(new Color(0, 255, 255));

        programButton.setPreferredSize(buttonSize);

        programButton.setFont(buttonFont);

        programButton.setBackground(new Color(0, 255, 255));

        programButton.setForeground(Color.white);
        programButton.setBorderPainted(false);

        add(titleLabel, BorderLayout.CENTER);

        add(leftArrowButton, BorderLayout.WEST);

        leftArrowButton.addActionListener(backAction);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, screenSize.height / 10);
    }
}
