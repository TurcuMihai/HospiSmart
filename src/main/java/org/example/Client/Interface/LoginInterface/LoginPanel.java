package org.example.Client.Interface.LoginInterface;

import org.example.Client.Actions.ClearAction;
import org.example.Client.Actions.ExitAction;
import org.example.Client.Actions.LoginAction;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * clasa responsabila cu construirea panel-ului de introducere a datelor pentru conectare
 */
public class LoginPanel extends JPanel {
    MainFrame mainFrame;

    BufferedImage image;

    JLabel usernameLabel, passwordLabel;
    JLabel errorLabel;
    int errorFound = 0;
    SpringLayout layout = new SpringLayout();
    JTextField usernameField, passwordField;

    JButton loginButton = new JButton("Conectare");
    JButton exitButton = new JButton("Iesire");
    JButton clearButton = new JButton("Anulare");
    ExitAction exitAction;
    ClearAction clearAction;
    LoginAction loginAction;

    /**
     * constructor pentru obiecte de tipul LoginPanel
     *
     * @param frame
     */
    public LoginPanel(MainFrame frame) {
        this.mainFrame = frame;
        exitAction = new ExitAction(mainFrame, mainFrame.getApplicationClient());
        clearAction = new ClearAction(mainFrame);
        loginAction = new LoginAction(mainFrame, mainFrame.getApplicationClient());
        init();
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public void setErrorFound(int state) {
        errorFound = state;
        if (state == 1) {
            errorLabel.setForeground(Color.RED);
        } else {
            errorLabel.setForeground(Color.white);
        }
    }

    /**
     * metoda responsabila de constructia panel-ului
     */
    public void init() {
        this.setBackground(Color.white);
        setLayout(new GridBagLayout());

        usernameLabel = new JLabel("Nume de utilizator:");
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Parola:");
        passwordField = new JPasswordField(20);

        errorLabel = new JLabel("Nume de utilizator sau parola invalide!");

        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        errorLabel.setFont(labelFont);
        errorLabel.setForeground(Color.white);
        passwordField.setFont(textFieldFont);
        usernameField.setFont(textFieldFont);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        loginButton.setFont(labelFont);
        clearButton.setFont(labelFont);
        exitButton.setFont(labelFont);
        loginButton.setBackground(Color.lightGray);
        exitButton.setBackground(Color.lightGray);
        clearButton.setBackground(Color.lightGray);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 0, 10);
        add(usernameLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        add(usernameField, c);

        c.gridx = 0;
        c.gridy = 1;
        add(passwordLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        add(passwordField, c);


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(30, 10, 0, 10);
        add(clearButton, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = clearButton.getPreferredSize().width - loginButton.getPreferredSize().width;
        c.insets = new Insets(30, 10, 0, 10);
        add(loginButton, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipadx = clearButton.getPreferredSize().width - exitButton.getPreferredSize().width;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(30, 10, 0, 10);
        add(exitButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 100, 0, 10);
        add(errorLabel, c);

        exitButton.addActionListener(exitAction);
        clearButton.addActionListener(clearAction);
        loginButton.addActionListener(loginAction);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension((screenSize.width / 10) * 8, 6 * (screenSize.height / 8));
    }
}
