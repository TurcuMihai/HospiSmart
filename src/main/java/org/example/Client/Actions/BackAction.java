package org.example.Client.Actions;

import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "<-"
 */
public class BackAction extends AbstractAction {

    MainFrame mainFrame;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame frame-ul aplicatiei
     */
    public BackAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        putValue(Action.NAME, "Back");
    }

    /**
     * Actiunea realizata in urma apasarii butonului "<-"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.showMenuPage();
    }
}
