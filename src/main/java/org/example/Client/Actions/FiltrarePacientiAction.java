package org.example.Client.Actions;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Obiect creat pentru a implementa actiunea butonului "Aplica" din fereastra "Pacienti"
 */
public class FiltrarePacientiAction extends AbstractAction {
    MainFrame mainFrame;

    ApplicationClient applicationClient;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     * @param mainFrame frame-ul aplicatiei
     * @param applicationClient obiectul de tip "Thread" al clientului (realizeaza comunicarea cu serverul)
     */
    public FiltrarePacientiAction (MainFrame mainFrame, ApplicationClient applicationClient){
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
            mainFrame.setDoctor(mainFrame.getPatientsCrudAndFiltersPanel().getDoctoriFieldFiltre());
            System.out.println(mainFrame.getDoctor());

            Date date = mainFrame.getPatientsCrudAndFiltersPanel().getDateChooserFiltre().getDate();
            if (date != null) {
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int year = localDate.getYear();
                int month = localDate.getMonthValue();
                int day = localDate.getDayOfMonth();

                String data = day + "-" + month + "-" + year;
                mainFrame.setData(data);
            } else {
                mainFrame.setData("Toate");
            }
            mainFrame.showPatientsPage();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
