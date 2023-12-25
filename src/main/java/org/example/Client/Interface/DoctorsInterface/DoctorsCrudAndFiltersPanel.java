package org.example.Client.Interface.DoctorsInterface;

import org.example.Client.Actions.*;
import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * clasa responsabila cu construirea panel-ului de filtre si butoane de accesare a datelor  din cadrul paginii "doctori"
 */
public class DoctorsCrudAndFiltersPanel extends JPanel {

    private ApplicationClient applicationClient;

    private MainFrame mainFrame;

    AnuleazaDoctorAction anuleazaAction;

    ActualizareDoctorAction actualizareDoctorAction;

    FiltrareDoctoriAction filtrareDoctoriAction;

    StergereDoctorAction stergereAction;

    AdaugaDoctorAction adaugaAction;

    JLabel idLabel = new JLabel("Id:");

    JLabel numeLabel = new JLabel("Nume:");

    JLabel prenumeLabel = new JLabel("Prenume:");

    JLabel telefonLabel = new JLabel("Telefon:");

    JLabel specializareLabel = new JLabel("Specializare:");

    JLabel cabinetLabel = new JLabel("Cabinet:");

    JTextField idField = new JTextField(15);

    JTextField numeField = new JTextField(15);
    JTextField prenumeField = new JTextField(15);
    JTextField telefonField = new JTextField(15);

    String[] optiuniSpecializari = {"Pediatrie", "Cardiologie", "Ortopedie", "Neurologie", "Stomatologie", "Oftalmologie"};
    JComboBox<String> specializariField = new JComboBox<>(optiuniSpecializari);

    String[] optiuniCabinete = {"C101", "C102", "C103", "C104", "C201", "C112", "C403", "C901"};
    JComboBox<String> cabineteField = new JComboBox<>(optiuniCabinete);

    JButton adaugaButton = new JButton("Adauga");
    JButton clearButton = new JButton("Anuleaza");

    JButton deleteButton = new JButton("Sterge");

    JButton updateButton = new JButton("Actualizeaza");

    JLabel labelFiltre = new JLabel("Filtreza dupa:");

    JLabel specializareLabelFiltre = new JLabel("♦ Specializare:");

    JLabel cabinetLabelFiltre = new JLabel("♦ Cabinet:");

    String[] optiuniSpecializariFiltre = {"Toate", "Pediatrie", "Cardiologie", "Ortopedie", "Neurologie", "Stomatologie", "Oftalmologie"};
    JComboBox<String> specializariFieldFiltre = new JComboBox<>(optiuniSpecializariFiltre);

    String[] optiuniCabineteFiltre = {"Toate", "C101", "C102", "C103", "C104", "C201", "C112", "C403", "C901"};
    JComboBox<String> cabineteFieldFiltre = new JComboBox<>(optiuniCabineteFiltre);

    JButton aplicaFiltre = new JButton("Aplica");

    /**
     * constructor pentru obiecte de tipul DoctorsCrudAndFiltersPanel
     *
     * @param mainFrame
     * @param applicationClient
     */
    public DoctorsCrudAndFiltersPanel(MainFrame mainFrame, ApplicationClient applicationClient) {
        this.mainFrame = mainFrame;
        this.applicationClient = applicationClient;
        init();
    }

    /**
     * metoda responsabila de construirea panel-ului
     */
    public void init() {
        this.setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        anuleazaAction = new AnuleazaDoctorAction(mainFrame);
        adaugaAction = new AdaugaDoctorAction(mainFrame, applicationClient);
        stergereAction = new StergereDoctorAction(mainFrame, applicationClient);
        actualizareDoctorAction = new ActualizareDoctorAction(mainFrame, applicationClient);
        filtrareDoctoriAction = new FiltrareDoctoriAction(mainFrame, applicationClient);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 0, 10);
        idLabel.setFont(labelFont);
        add(idLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 10, 0, 0);
        idField.setFont(textFieldFont);
        add(idField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        numeLabel.setFont(labelFont);
        add(numeLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 0, 0);
        numeField.setFont(textFieldFont);
        add(numeField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        prenumeLabel.setFont(labelFont);
        add(prenumeLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 10, 0, 0);
        prenumeField.setFont(textFieldFont);
        add(prenumeField, c);

        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(0, 70, 0, 0);

        telefonLabel.setFont(labelFont);
        add(telefonLabel, c);

        c.gridx = 3;
        c.gridy = 0;
        c.insets = new Insets(0, 10, 0, 0);
        telefonField.setFont(textFieldFont);
        add(telefonField, c);

        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0, 70, 0, 0);

        specializareLabel.setFont(labelFont);
        add(specializareLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 0, 0);
        specializariField.setFont(textFieldFont);
        add(specializariField, c);

        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 70, 0, 0);
        cabinetLabel.setFont(labelFont);
        add(cabinetLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        c.insets = new Insets(0, 10, 0, 0);
        cabineteField.setFont(textFieldFont);
        add(cabineteField, c);

        c.gridx = 4;
        c.gridy = 0;
        c.insets = new Insets(0, 70, 0, 0);
        adaugaButton.setFont(labelFont);
        add(adaugaButton, c);

        c.gridx = 4;
        c.gridy = 1;
        c.insets = new Insets(10, 70, 0, 0);
        updateButton.setFont(labelFont);
        add(updateButton, c);

        c.gridx = 4;
        c.gridy = 2;
        c.insets = new Insets(10, 70, 0, 0);
        clearButton.setFont(labelFont);
        add(clearButton, c);

        c.gridx = 4;
        c.gridy = 3;
        c.insets = new Insets(10, 70, 0, 0);
        deleteButton.setFont(labelFont);
        add(deleteButton, c);

        c.gridx = 5;
        c.gridy = 0;
        c.insets = new Insets(0, 100, 0, 0);
        labelFiltre.setFont(labelFont);
        add(labelFiltre, c);

        c.gridx = 6;
        c.gridy = 1;
        c.insets = new Insets(0, 70, 0, 0);
        specializareLabelFiltre.setFont(labelFont);
        add(specializareLabelFiltre, c);

        c.gridx = 6;
        c.gridy = 2;
        cabinetLabelFiltre.setFont(labelFont);
        add(cabinetLabelFiltre, c);

        c.gridx = 7;
        c.gridy = 1;
        specializariFieldFiltre.setFont(textFieldFont);
        add(specializariFieldFiltre, c);

        c.gridx = 7;
        c.gridy = 2;
        cabineteFieldFiltre.setFont(textFieldFont);
        add(cabineteFieldFiltre, c);

        c.gridx = 7;
        c.gridy = 4;
        aplicaFiltre.setFont(labelFont);
        add(aplicaFiltre, c);


        clearButton.addActionListener(anuleazaAction);
        adaugaButton.addActionListener(adaugaAction);
        deleteButton.addActionListener(stergereAction);
        updateButton.addActionListener(actualizareDoctorAction);
        aplicaFiltre.addActionListener(filtrareDoctoriAction);

    }

    public JComboBox<String> getCabineteFieldFiltre() {
        return cabineteFieldFiltre;
    }

    public JComboBox<String> getSpecializariFieldFiltre() {
        return specializariFieldFiltre;
    }

    public JTextField getIdField() {
        return idField;
    }

    public String getSpecializareText() {
        return specializariField.getSelectedItem().toString();
    }

    public String getCabinetText() {
        return cabineteField.getSelectedItem().toString();
    }

    public JTextField getPrenumeField() {
        return prenumeField;
    }

    public JTextField getNumeField() {
        return numeField;
    }

    public JTextField getTelefonField() {
        return telefonField;
    }


    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, 4 * (screenSize.height / 10));
    }
}
