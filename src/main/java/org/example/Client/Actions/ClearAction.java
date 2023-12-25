package org.example.Client.Actions;

import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "Anuleaza" din fereastra "Login"
 */
public class ClearAction extends AbstractAction {

    MainFrame mainFrame;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param loginMainFrame frame-ul aplicatiei
     */
    public ClearAction(MainFrame loginMainFrame) {
        this.mainFrame = loginMainFrame;
        putValue(Action.NAME, "Clear");
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Anuleaza"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.getLoginPanel().getUsernameField().setText("");
        mainFrame.getLoginPanel().getPasswordField().setText("");
    }
}
