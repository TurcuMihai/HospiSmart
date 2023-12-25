package org.example.Client.Actions;

import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Obiect creat pentru a implementa actiunea butonului "Programari pacienti"
 */
public class PatientsAction extends AbstractAction {
    private MainFrame mainFrame;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame frame-ul aplicatiei
     */
    public PatientsAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Programari pacienti"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        try {
            this.mainFrame.setQuery("pacienti");
            this.mainFrame.setData("Toate");
            this.mainFrame.setDoctor("Toate");
            this.mainFrame.showPatientsPage();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
