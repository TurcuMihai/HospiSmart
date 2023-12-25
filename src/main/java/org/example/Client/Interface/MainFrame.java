package org.example.Client.Interface;

import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.DoctorsInterface.DoctorsCrudAndFiltersPanel;
import org.example.Client.Interface.DoctorsInterface.DoctorsHeaderPanel;
import org.example.Client.Interface.DoctorsInterface.DoctorsListPanel;
import org.example.Client.Interface.LoginInterface.BottomPanel;
import org.example.Client.Interface.LoginInterface.HeaderPanel;
import org.example.Client.Interface.LoginInterface.LoginPanel;
import org.example.Client.Interface.LoginInterface.MarginPanel;
import org.example.Client.Interface.MenuInterface.MenuHeaderPanel;
import org.example.Client.Interface.MenuInterface.MenuPanel;
import org.example.Client.Interface.PatientsInterface.PatientsCrudAndFiltersPanel;
import org.example.Client.Interface.PatientsInterface.PatientsHeaderPanel;
import org.example.Client.Interface.PatientsInterface.PatientsListPanel;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Fereasta principala a aplicatiei
 */

public class MainFrame extends JFrame {


    private String query;
    private String specializare;
    private String cabinet;

    private String data;

    private String doctor;

    HeaderPanel headerPanel;

    ApplicationClient applicationClient;

    BottomPanel bottomPanel;

    LoginPanel loginPanel;

    MarginPanel marginPanelLeft;
    MarginPanel marginPanelRight;

    MenuHeaderPanel menuHeaderPanel;

    MenuPanel menuPanel;

    DoctorsHeaderPanel doctorsHeaderPanel;

    DoctorsListPanel doctorsListPanel;

    DoctorsCrudAndFiltersPanel doctorsCrudAndFiltersPanel;

    PatientsHeaderPanel patientsHeaderPanel;

    PatientsCrudAndFiltersPanel patientsCrudAndFiltersPanel;

    PatientsListPanel patientsListPanel;

    /**
     * Constructor pentru obiecte de tip MainFrame
     * @param applicationClient thread-ul clientului ce comunica cu server-ul
     */

    public MainFrame(ApplicationClient applicationClient) {
        super("Hospital");
        this.applicationClient = applicationClient;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        showLoginPage();
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public DoctorsListPanel getDoctorsListPanel() {
        return this.doctorsListPanel;
    }

    public String getQuery() {
        return query;
    }

    public String getSpecializare() {
        return this.specializare;
    }

    public String getCabinet() {
        return this.cabinet;
    }

    public String getDoctor() {
        return this.doctor;
    }

    public String getData() {
        return this.data;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PatientsListPanel getPatientsListPanel() {
        return this.patientsListPanel;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public DoctorsCrudAndFiltersPanel getDoctorsCrudAndFiltersPanel() {
        return doctorsCrudAndFiltersPanel;
    }

    public PatientsCrudAndFiltersPanel getPatientsCrudAndFiltersPanel() {
        return patientsCrudAndFiltersPanel;
    }

    public LoginPanel getLoginPanel() {
        return this.loginPanel;
    }

    public ApplicationClient getApplicationClient() {
        return this.applicationClient;
    }

    /**
     * Metoda ce afiseaza pagina de Login
     */
    public void showLoginPage() {
        if (menuHeaderPanel != null && menuPanel != null) {
            this.remove(menuHeaderPanel);
            this.remove(menuPanel);
            this.revalidate();
        }

        setLayout(new BorderLayout());
        headerPanel = new HeaderPanel(this);
        loginPanel = new LoginPanel(this);
        bottomPanel = new BottomPanel(this);
        marginPanelLeft = new MarginPanel(this);
        marginPanelRight = new MarginPanel(this);
        add(headerPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(loginPanel, BorderLayout.CENTER);
        add(marginPanelRight, BorderLayout.EAST);
        add(marginPanelLeft, BorderLayout.WEST);
        pack();
    }

    /**
     * Metoda ce afiseaza pagina Meniu
     */
    public void showMenuPage() {
        if (doctorsListPanel != null && doctorsHeaderPanel != null && doctorsCrudAndFiltersPanel != null) {
            this.remove(doctorsHeaderPanel);
            this.remove(doctorsListPanel);
            this.remove(doctorsCrudAndFiltersPanel);
        }
        if (patientsListPanel != null && patientsHeaderPanel != null && patientsCrudAndFiltersPanel != null) {
            this.remove(patientsListPanel);
            this.remove(patientsHeaderPanel);
            this.remove(patientsCrudAndFiltersPanel);
        }
        this.remove(marginPanelLeft);
        this.remove(marginPanelRight);
        this.remove(headerPanel);
        this.remove(bottomPanel);
        this.remove(loginPanel);
        this.revalidate();
        setLayout(new BorderLayout());
        menuHeaderPanel = new MenuHeaderPanel(this);
        menuPanel = new MenuPanel(this);

        add(menuHeaderPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        pack();
    }

    /**
     * Metoda ce afiseaza pagina Doctori
     * @throws IOException
     */
    public void showDoctorsPage() throws IOException {
        if (menuHeaderPanel != null && menuPanel != null) {
            this.remove(menuHeaderPanel);
            this.remove(menuPanel);
        }
        if (doctorsListPanel != null) {
            this.remove(doctorsListPanel);
            this.remove(doctorsHeaderPanel);
            this.remove(doctorsCrudAndFiltersPanel);
        }
        setLayout(new BorderLayout());
        doctorsHeaderPanel = new DoctorsHeaderPanel(this);
        doctorsCrudAndFiltersPanel = new DoctorsCrudAndFiltersPanel(this, applicationClient);
        doctorsListPanel = new DoctorsListPanel(this, applicationClient);

        add(doctorsHeaderPanel, BorderLayout.NORTH);
        add(doctorsCrudAndFiltersPanel, BorderLayout.CENTER);
        add(doctorsListPanel, BorderLayout.SOUTH);

        pack();
    }

    /**
     * Metoda ce afiseaza pagina Programari pacienti
     * @throws IOException
     */
    public void showPatientsPage() throws IOException {
        if (menuHeaderPanel != null && menuPanel != null) {
            this.remove(menuHeaderPanel);
            this.remove(menuPanel);
            this.revalidate();
        }

        if (patientsListPanel != null && patientsHeaderPanel != null && patientsCrudAndFiltersPanel != null) {
            this.remove(patientsListPanel);
            this.remove(patientsHeaderPanel);
            this.remove(patientsCrudAndFiltersPanel);
            this.revalidate();
        }

        setLayout(new BorderLayout());
        patientsHeaderPanel = new PatientsHeaderPanel(this);
        patientsCrudAndFiltersPanel = new PatientsCrudAndFiltersPanel(this, applicationClient);
        patientsListPanel = new PatientsListPanel(this, applicationClient);

        add(patientsHeaderPanel, BorderLayout.NORTH);
        add(patientsCrudAndFiltersPanel, BorderLayout.CENTER);
        add(patientsListPanel, BorderLayout.SOUTH);

        pack();
    }

}
