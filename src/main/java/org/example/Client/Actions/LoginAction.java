package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;
import org.example.Client.Interface.LoginInterface.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "Login"
 */
public class LoginAction extends AbstractAction {

    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param loginMainFrame    frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public LoginAction(MainFrame loginMainFrame, ApplicationClient applicationClient) {
        this.applicationClient = applicationClient;
        this.mainFrame = loginMainFrame;
    }

    /**
     * verifica daca inputul este valid
     *
     * @param loginPanel panel-ul din care sunt luate datele introduse de utilizator
     * @return true daca este valid, false altfel
     */
    public boolean isValidInput(LoginPanel loginPanel) {
        if (!loginPanel.getUsernameField().getText().equals("") && !loginPanel.getPasswordField().getText().equals("")) {
            applicationClient.callLogin(loginPanel.getUsernameField().getText(), loginPanel.getPasswordField().getText());
            return true;
        }
        return false;
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Conectare"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isValidInput(mainFrame.getLoginPanel())) {
            mainFrame.getLoginPanel().setErrorFound(0);
            System.out.println("Input valid.");
        } else {
            System.out.println("Input invalid.");
            mainFrame.getLoginPanel().setErrorFound(1);
        }
    }
}
