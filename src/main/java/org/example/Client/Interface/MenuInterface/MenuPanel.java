package org.example.Client.Interface.MenuInterface;

import org.example.Client.Actions.*;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * clasa responsabila cu construirea paginii de meniu
 */
public class MenuPanel extends JPanel {

    MainFrame mainFrame;

    JButton doctorsButton = new JButton("Doctori");

    JButton patientsButton = new JButton("Programari pacienti");

    JButton logoutButton = new JButton("Deconectare");

    JButton exitButton = new JButton("Iesire");

    DoctorsAction doctorsAction;

    PatientsAction patientsAction;

    LogoutAction logoutAction;

    ExitAction exitAction;

    Dimension buttonSize = new Dimension(400, 100);

    Font buttonFont = new Font("Arial", Font.PLAIN, 30);

    /**
     * constructor pentru obiecte de tipul MenuPanel
     *
     * @param mainFrame
     */
    public MenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        doctorsAction = new DoctorsAction(mainFrame);
        patientsAction = new PatientsAction(mainFrame);
        logoutAction = new LogoutAction(mainFrame);
        exitAction = new ExitAction(mainFrame, mainFrame.getApplicationClient());
        init();
    }

    /**
     * metoda responsabila de construirea meniului
     */
    public void init() {
        this.setBackground(Color.white);

        doctorsButton.setPreferredSize(buttonSize);
        patientsButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        doctorsButton.setFont(buttonFont);
        patientsButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        doctorsButton.setBackground(new Color(77, 210, 255));
        patientsButton.setBackground(new Color(77, 210, 255));
        logoutButton.setBackground(new Color(77, 210, 255));
        exitButton.setBackground(new Color(77, 210, 255));

        doctorsButton.setForeground(Color.WHITE);
        patientsButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(50, 10, 0, 10);
        add(doctorsButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(50, 10, 0, 10);
        add(patientsButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(50, 10, 0, 10);
        add(logoutButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(50, 10, 0, 10);
        add(exitButton, c);

        exitButton.addActionListener(exitAction);
        logoutButton.addActionListener(logoutAction);
        doctorsButton.addActionListener(doctorsAction);
        patientsButton.addActionListener(patientsAction);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, 9 * (screenSize.height / 10));
    }
}
