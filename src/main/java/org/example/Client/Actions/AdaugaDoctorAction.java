package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.DoctorsInterface.DoctorsCrudAndFiltersPanel;
import org.example.Client.Interface.MainFrame;
import org.example.Client.Interface.MessageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Obiect creat pentru a implementa actiunea butonului "Adaugare" din fereastra "Doctori"
 */
public class AdaugaDoctorAction extends AbstractAction {
    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame         frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public AdaugaDoctorAction (MainFrame mainFrame, ApplicationClient applicationClient){
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
        if (!doctorsCrudAndFiltersPanel.getNumeField().getText().equals("") &&
            !doctorsCrudAndFiltersPanel.getPrenumeField().getText().equals("") &&
                !doctorsCrudAndFiltersPanel.getTelefonField().getText().equals("")) {

            applicationClient.callAdaugareDoctor(doctorsCrudAndFiltersPanel.getNumeField().getText(),
                    doctorsCrudAndFiltersPanel.getPrenumeField().getText(),
                    doctorsCrudAndFiltersPanel.getTelefonField().getText(),
                    doctorsCrudAndFiltersPanel.getSpecializareText(),
                    doctorsCrudAndFiltersPanel.getCabinetText());
            return true;
        }
        return false;
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Actualizare"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isValidInput(mainFrame.getDoctorsCrudAndFiltersPanel())) {
            mainFrame.setCabinet("Toate");
            mainFrame.setSpecializare("Toate");
            MessageFrame messageFrame = new MessageFrame("Inserare realizata cu succes!");
            System.out.println("Input valid.");
        } else {
            MessageFrame messageFrame = new MessageFrame("Date invalide!");
            System.out.println("Input invalud.");
        }
    }
}
