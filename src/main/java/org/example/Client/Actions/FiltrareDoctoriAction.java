package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Obiect creat pentru a implementa actiunea butonului "Aplica" din fereastra "Doctori"
 */
public class FiltrareDoctoriAction extends AbstractAction {
    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame         frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public FiltrareDoctoriAction(MainFrame mainFrame, ApplicationClient applicationClient) {
        this.applicationClient = applicationClient;
        this.mainFrame = mainFrame;
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Aplica"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            mainFrame.setSpecializare(mainFrame.getDoctorsCrudAndFiltersPanel().getSpecializariFieldFiltre().getSelectedItem().toString());
            mainFrame.setCabinet(mainFrame.getDoctorsCrudAndFiltersPanel().getCabineteFieldFiltre().getSelectedItem().toString());
            mainFrame.showDoctorsPage();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
