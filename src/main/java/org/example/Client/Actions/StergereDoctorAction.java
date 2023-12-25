package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.DoctorsInterface.DoctorsCrudAndFiltersPanel;
import org.example.Client.Interface.MainFrame;
import org.example.Client.Interface.MessageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "Stergere"
 */
public class StergereDoctorAction extends AbstractAction {
    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame    frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public StergereDoctorAction (MainFrame mainFrame, ApplicationClient applicationClient){
        this.applicationClient = applicationClient;
        this.mainFrame = mainFrame;
    }

    /**
     * verifica daca inputul este valid
     *
     * @param doctorsCrudAndFiltersPanel panel-ul din care sunt luate datele introduse de utilizator
     * @return true daca este valid, false altfel
     */
    public boolean isValidInput (DoctorsCrudAndFiltersPanel doctorsCrudAndFiltersPanel) {
        if (!doctorsCrudAndFiltersPanel.getIdField().getText().equals("")) {
            try {
                int id = Integer.parseInt(doctorsCrudAndFiltersPanel.getIdField().getText());
                applicationClient.callStergereDoctor(id);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
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
        if(isValidInput(mainFrame.getDoctorsCrudAndFiltersPanel())) {
            mainFrame.setCabinet("Toate");
            mainFrame.setSpecializare("Toate");
            MessageFrame messageFrame = new MessageFrame("Stergere realizata cu succes!");
            System.out.println("Input valid.");
        } else {
            MessageFrame messageFrame = new MessageFrame("ID invalid!");
            System.out.println("Input invalid.");
        }
    }
}