package org.example.Client.Actions;

import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "Logout"
 */
public class LogoutAction extends AbstractAction {
    MainFrame mainFrame;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame frame-ul aplicatiei
     */
    public LogoutAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

    }

    /**
     * Actiunea realizata in urma apasarii butonului "Deconectare"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.mainFrame.showLoginPage();
    }
}
