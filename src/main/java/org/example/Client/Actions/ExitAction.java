package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "Iesire"
 */
public class ExitAction extends AbstractAction {

    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param loginMainFrame         frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public ExitAction (MainFrame loginMainFrame, ApplicationClient applicationClient){
        this.applicationClient = applicationClient;
        this.mainFrame = loginMainFrame;
        putValue(Action.NAME, "Exit");
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Iesire"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        // logica din momentul in care actiunea este initializata
        applicationClient.callExit();
        mainFrame.dispose();
        System.exit(1);
    }
}
