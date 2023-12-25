package org.example.Client.Actions;

import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Obiect creat pentru a implementa actiunea butonului "Doctori" din pagina "Meniu"
 */
public class DoctorsAction extends AbstractAction {
    private MainFrame mainFrame;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame frame-ul aplicatiei
     */
    public DoctorsAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Doctori" din pagina "Meniu"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.mainFrame.setQuery("doctori");
            this.mainFrame.setSpecializare("Toate");
            this.mainFrame.setCabinet("Toate");
            this.mainFrame.showDoctorsPage();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
