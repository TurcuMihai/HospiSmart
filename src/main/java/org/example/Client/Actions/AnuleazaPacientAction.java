package org.example.Client.Actions;

import org.example.Client.Interface.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Obiect creat pentru a implementa actiunea butonului "Actualizare" din fereastra "Doctori"
 */
public class AnuleazaPacientAction extends AbstractAction {

    MainFrame mainFrame;

    /**
     * Constructor ce seteaza valorile necesare pentru a implementa logica aplicatiei
     *
     * @param mainFrame frame-ul aplicatiei
     */
    public AnuleazaPacientAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        putValue(Action.NAME, "Anuleaza");
    }

    /**
     * Actiunea realizata in urma apasarii butonului "Anuleaza"
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        mainFrame.getPatientsCrudAndFiltersPanel().getNumeField().setText("");
        mainFrame.getPatientsCrudAndFiltersPanel().getIdField().setText("");
        mainFrame.getPatientsCrudAndFiltersPanel().getPrenumeField().setText("");
        mainFrame.getPatientsCrudAndFiltersPanel().getTelefonField().setText("");
        mainFrame.getPatientsCrudAndFiltersPanel().getIdDoctorField().setText("");
    }
}
