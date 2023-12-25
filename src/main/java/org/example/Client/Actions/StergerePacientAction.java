package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;
import org.example.Client.Interface.MessageFrame;
import org.example.Client.Interface.PatientsInterface.PatientsCrudAndFiltersPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Obiect creat pentru a implementa actiunea butonului "Stergere"
 */
public class StergerePacientAction extends AbstractAction {
    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame         frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public StergerePacientAction(MainFrame mainFrame, ApplicationClient applicationClient) {
        this.applicationClient = applicationClient;
        this.mainFrame = mainFrame;
    }

    /**
     * verifica daca inputul este valid
     *
     * @param patientsCrudAndFiltersPanel panel-ul din care sunt luate datele introduse de utilizator
     * @return true daca este valid, false altfel
     */
    public boolean isValidInput(PatientsCrudAndFiltersPanel patientsCrudAndFiltersPanel) {
        if (!patientsCrudAndFiltersPanel.getIdField().getText().equals("")) {
            try {
                int id = Integer.parseInt(patientsCrudAndFiltersPanel.getIdField().getText());
                applicationClient.callStergerePacient(id);
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
        if (isValidInput(mainFrame.getPatientsCrudAndFiltersPanel())) {
            mainFrame.setDoctor("Toate");
            mainFrame.setData("Toate");
            MessageFrame messageFrame = new MessageFrame("Stergere realizata cu succes!");
            System.out.println("Input valid.");
        } else {
            MessageFrame messageFrame = new MessageFrame("ID invalid!");
            System.out.println("Input invalid.");
        }
    }
}
