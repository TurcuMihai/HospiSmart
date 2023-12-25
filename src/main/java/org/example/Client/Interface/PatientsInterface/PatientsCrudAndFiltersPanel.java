package org.example.Client.Interface.PatientsInterface;

import com.toedter.calendar.JDateChooser;
import org.example.Client.Actions.*;
import org.example.Client.ApplicationClient.ApplicationClient;
import org.example.Client.Interface.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

/**
 * clasa responsabila cu construirea panel-ului de filtre si butoane de accesare a datelor  din cadrul paginii "programari"
 */
public class PatientsCrudAndFiltersPanel extends JPanel {

    private MainFrame mainFrame;
    private PrintWriter output;

    private BufferedReader input;

    ApplicationClient applicationClient;
    JLabel idLabel = new JLabel("Id:");

    JLabel numeLabel = new JLabel("Nume:");

    JLabel prenumeLabel = new JLabel("Prenume:");

    JLabel telefonLabel = new JLabel("Telefon:");

    JLabel idDoctorLabel = new JLabel("ID_Doctor:");

    JLabel oraProgramariiLabel = new JLabel("Ora programarii: ");

    JLabel dataProgramariiLabel = new JLabel("Data programarii: ");

    JTextField idField = new JTextField(15);

    JTextField numeField = new JTextField(15);
    JTextField prenumeField = new JTextField(15);
    JTextField telefonField = new JTextField(15);

    JTextField idDoctorField = new JTextField(15);

    String[] optiuniProgramari = {"9:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
    JComboBox<String> oraField = new JComboBox<>(optiuniProgramari);

    JDateChooser dateChooser = new JDateChooser();

    JButton adaugaButton = new JButton("Adauga");
    JButton clearButton = new JButton("Anuleaza");

    JButton deleteButton = new JButton("Sterge");

    JButton updateButton = new JButton("Actualizeaza");

    JLabel labelFiltre = new JLabel("Filtreza dupa:");

    JLabel doctorLabelFiltre = new JLabel("♦ Doctor:");

    JLabel dataLabelFiltre = new JLabel("♦ Data:");

    JDateChooser dateChooserFiltre = new JDateChooser();

    Vector<String> optiuniDoctoriFiltre = new Vector<>();
    JComboBox<String> doctoriFieldFiltre;

    JButton aplicaFiltre = new JButton("Aplica");

    AnuleazaPacientAction anuleazaPacientAction;

    StergerePacientAction stergerePacientAction;

    AdaugaPacientAction adaugaPacientAction;

    ActualizarePacientAction actualizarePacientAction;

    FiltrarePacientiAction filtrarePacientiAction;

    /**
     * constructor pentru obiecte de tipul PatientsCrudAndFiltersPanel
     *
     * @param mainFrame
     * @param applicationClient
     */
    public PatientsCrudAndFiltersPanel(MainFrame mainFrame, ApplicationClient applicationClient) throws IOException {
        this.mainFrame = mainFrame;
        this.applicationClient = applicationClient;
        output = new PrintWriter(applicationClient.getSocket().getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(applicationClient.getSocket().getInputStream()));
        init();
    }

    public JTextField getIdField() {
        return idField;
    }

    public String getOra() {
        return oraField.getSelectedItem().toString();
    }

    public JTextField getNumeField() {
        return numeField;
    }

    public JTextField getPrenumeField() {
        return prenumeField;
    }

    public JTextField getIdDoctorField() {
        return idDoctorField;
    }

    public JTextField getTelefonField() {
        return telefonField;
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    public void addDoctor(String id) {
        optiuniDoctoriFiltre.add(id);
    }

    /**
     * metoda responsabila de construirea panel-ului
     */
    public void init() {
        if (mainFrame.getData() == "") {
            mainFrame.setData("Toate");
        }

        optiuniDoctoriFiltre.add("Toate");
        output.println("id doctori");

        dateChooserFiltre.setPreferredSize(new Dimension(200, dateChooser.getPreferredSize().height));
        this.setBackground(Color.WHITE);
        // cred ca asta va fi
        setLayout(new GridBagLayout());
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        anuleazaPacientAction = new AnuleazaPacientAction(mainFrame);
        stergerePacientAction = new StergerePacientAction(mainFrame, applicationClient);
        adaugaPacientAction = new AdaugaPacientAction(mainFrame, applicationClient);
        actualizarePacientAction = new ActualizarePacientAction(mainFrame, applicationClient);
        filtrarePacientiAction = new FiltrarePacientiAction(mainFrame, applicationClient);

        System.out.println(optiuniDoctoriFiltre);
        doctoriFieldFiltre = new JComboBox<>(optiuniDoctoriFiltre);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 10);
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
        idDoctorLabel.setFont(labelFont);
        add(idDoctorLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 0, 0);
        idDoctorField.setFont(textFieldFont);
        add(idDoctorField, c);

        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 70, 0, 0);
        oraProgramariiLabel.setFont(labelFont);
        add(oraProgramariiLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        c.insets = new Insets(0, 10, 0, 0);
        oraField.setFont(textFieldFont);
        add(oraField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 10);
        dataProgramariiLabel.setFont(labelFont);
        add(dataProgramariiLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(0, 10, 0, 0);
        dateChooser.setFont(textFieldFont);
        add(dateChooser, c);

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
        c.insets = new Insets(0, 30, 0, 0);
        labelFiltre.setFont(labelFont);
        add(labelFiltre, c);

        c.gridx = 5;
        c.gridy = 1;
        c.insets = new Insets(0, 70, 0, 0);
        doctorLabelFiltre.setFont(labelFont);
        add(doctorLabelFiltre, c);

        c.gridx = 5;
        c.gridy = 2;
        dataLabelFiltre.setFont(labelFont);
        add(dataLabelFiltre, c);

        doctoriFieldFiltre = new JComboBox<>(optiuniDoctoriFiltre);
        c.gridx = 6;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 0, 0);
        doctoriFieldFiltre.setFont(textFieldFont);
        add(doctoriFieldFiltre, c);

        c.gridx = 6;
        c.gridy = 2;
        c.insets = new Insets(0, 10, 0, 0);
        dateChooserFiltre.setFont(textFieldFont);
        add(dateChooserFiltre, c);

        c.gridx = 6;
        c.gridy = 3;
        aplicaFiltre.setFont(labelFont);
        add(aplicaFiltre, c);

        clearButton.addActionListener(anuleazaPacientAction);
        deleteButton.addActionListener(stergerePacientAction);
        adaugaButton.addActionListener(adaugaPacientAction);
        updateButton.addActionListener(actualizarePacientAction);
        aplicaFiltre.addActionListener(filtrarePacientiAction);

    }

    public String getDoctoriFieldFiltre() {
        return doctoriFieldFiltre.getSelectedItem().toString();
    }

    public JDateChooser getDateChooserFiltre() {
        return dateChooserFiltre;
    }

    /**
     * metoda responsabila de transmiterea datelor despre pacienti
     */
    public void sendPatientInfo() {
        Date date = getDateChooser().getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        String data = day + "-" + month + "-" + year;
        output.println(getNumeField().getText());
        output.println(getPrenumeField().getText());
        output.println(getTelefonField().getText());
        output.println(getOra());
        output.println(data);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, 4 * (screenSize.height / 10));
    }
}
