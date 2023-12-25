package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.DoctorsInterface.DoctorsCrudAndFiltersPanel;
import org.example.Client.Interface.MainFrame;
import org.example.Client.Interface.MessageFrame;
import org.example.Client.Interface.PatientsInterface.PatientsCrudAndFiltersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Obiect creat pentru a implementa actiunea butonului "Actualizare" din fereastra "Doctori"
 */
public class AdaugaPacientAction extends AbstractAction {
    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame         frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public AdaugaPacientAction (MainFrame mainFrame, ApplicationClient applicationClient){
        this.applicationClient = applicationClient;
        this.mainFrame = mainFrame;
    }

    /**
     * verifica daca inputul este valid
     *
     * @param patientsCrudAndFiltersPanel panel-ul din care sunt luate datele introduse de utilizator
     * @return true daca este valid, false altfel
     */
    public boolean isValidInput (PatientsCrudAndFiltersPanel patientsCrudAndFiltersPanel) {
        if (!patientsCrudAndFiltersPanel.getNumeField().getText().equals("") &&
                !patientsCrudAndFiltersPanel.getPrenumeField().getText().equals("") &&
                !patientsCrudAndFiltersPanel.getTelefonField().getText().equals("") &&
                !patientsCrudAndFiltersPanel.getIdDoctorField().getText().equals("") &&
                !(patientsCrudAndFiltersPanel.getDateChooser().getDate() == null)) {
            try {
                int id = Integer.parseInt(patientsCrudAndFiltersPanel.getIdDoctorField().getText());
                // Prelucrearea datei
                Date date = patientsCrudAndFiltersPanel.getDateChooser().getDate();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int year = localDate.getYear();
                int month = localDate.getMonthValue();
                int day = localDate.getDayOfMonth();

                String data = day + "-" + month + "-" + year;
                applicationClient.callAdaugarePacient(
                        patientsCrudAndFiltersPanel.getNumeField().getText(),
                        patientsCrudAndFiltersPanel.getPrenumeField().getText(),
                        patientsCrudAndFiltersPanel.getTelefonField().getText(),
                        id,
                        patientsCrudAndFiltersPanel.getOra(),
                        data);
                return true;

            } catch (NumberFormatException e) {
                return false;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        if(isValidInput(mainFrame.getPatientsCrudAndFiltersPanel())) {
            mainFrame.setDoctor("Toate");
            mainFrame.setData("Toate");
       //     MessageFrame messageFrame = new MessageFrame("Inserare realizata cu succes!");
            System.out.println("Input valid.");
        } else {
            MessageFrame messageFrame = new MessageFrame("Date invalide!");
            System.out.println("Input invalid.");
        }
    }
}