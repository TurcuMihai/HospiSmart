package org.example.Client.ApplicationClient;

import org.example.Client.Interface.MainFrame;
import org.example.Client.Interface.MessageFrame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Obiectul ce extinde clasa "Thread" si realizeaza comunicarea cu serverul
 */
public class ApplicationClient extends Thread {
    private String host;

    private  int port;

    private Socket socket;

    private BufferedReader input;

    private PrintWriter output;

    private BufferedReader reader;

    private MainFrame mainFrame;

    /**
     * Constructor pentru obiecte de tip "ApplicationClient"
     * @param host host-ul aplicatiei
     * @param port portul aplicatiei
     */
    public ApplicationClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void setLoginMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Metoda ce trimite catre server o solicitare de conectare
     * @param username username-ul utilizatorului
     * @param password parola utilizatorului
     */
    public void callLogin (String username, String password) {
        output.println("login");
        output.println(username);
        output.println(password);
    }

    /**
     * Metoda ce solicita stergerea unui doctor
     * @param id id-ul doctorului
     */
    public void callStergereDoctor(Integer id) {
        output.println("stergere doctor");
        output.println(id);
    }

    /**
     * Metoda ce solicita stergerea unui pacient
     * @param id id-ul pacientului
     */
    public void callStergerePacient(Integer id) {
        output.println("stergere pacient");
        output.println(id);
    }

    /**
     * Metoda ce solicita actualizarea datelor unui doctor
     * @param id
     * @param nume
     * @param prenume
     * @param telefon
     * @param specializare
     * @param cabinet
     */
    public void callActualizareDoctor(Integer id, String nume, String prenume, String telefon, String specializare, String cabinet)
    {
        output.println("actualizare doctor");
        output.println(id);
        output.println(nume);
        output.println(prenume);
        output.println(telefon);
        output.println(specializare);
        output.println(cabinet);
    }

    /**
     * Metoda ce solicita actualizarea datelor unei programari a unui pacient
     * @param id
     * @param nume
     * @param prenume
     * @param telefon
     * @param id_doctor
     * @param data
     * @param ora
     */
    public void callActualizarePacient(Integer id,String nume,String prenume,String telefon,Integer id_doctor,String data, String ora) {
        output.println("actualizare pacient");
        output.println(id);
        output.println(nume);
        output.println(prenume);
        output.println(telefon);
        output.println(id_doctor);
        output.println(data);
        output.println(ora);
    }

    /**
     * Metoda ce solicita inserarea unei programari
     * @param nume
     * @param prenume
     * @param telefon
     * @param doctor_id
     * @param ora
     * @param data
     * @throws IOException
     */
    public void callAdaugarePacient(String nume, String prenume, String telefon,int doctor_id, String ora, String data) throws IOException {
        output.println("inserare pacient");
        output.println(doctor_id);
    }

    /**
     * Metoda ce solicita inserarea unui nou doctor
     * @param nume
     * @param prenume
     * @param telefon
     * @param specializare
     * @param cabinet
     */
    public void callAdaugareDoctor(String nume, String prenume, String telefon, String specializare, String cabinet) {
        output.println("inserare doctor");
        output.println(nume);
        output.println(prenume);
        output.println(telefon);
        output.println(specializare);
        output.println(cabinet);
    }

    /**
     * Metoda ce transmite server-ului un mesaj pentru deconectarea clientului
     */
    public void callExit () {
        output.println("exit");
    }

    public Socket getSocket () {
        return this.socket;
    }

    /**
     *  Metoda ce comunica cu serverul
     */
    public void start() {
        try {
             socket = new Socket(host, port);
             input = new BufferedReader(new InputStreamReader(System.in));
             output = new PrintWriter(socket.getOutputStream(), true);
             reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                if (inputLine.equals("Connected")) {
                    System.out.println(inputLine);
                    mainFrame.showMenuPage();
                } else if (inputLine.equals("Not connected")) {
                    System.out.println(inputLine);
                    mainFrame.getLoginPanel().setErrorFound(1);
                } else if (inputLine.equals("doctori")) {
                    inputLine = reader.readLine();
                    int rows = Integer.parseInt(inputLine);
                    Object[][] table = new Object[rows][6];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < 6; j++) {
                            inputLine = reader.readLine();
                            table[i][j] = inputLine;
                        }
                    }
                    mainFrame.getDoctorsListPanel().setRows(table);
                    mainFrame.pack();

                } else if (inputLine.equals("comanda executata cu succes doctori")) {
                    mainFrame.showDoctorsPage();
                } else if (inputLine.equals("comanda executata cu succes pacienti")) {
                    mainFrame.showPatientsPage();
                } else if (inputLine.equals("pacienti")) {
                    inputLine = reader.readLine();
                    int rows = Integer.parseInt(inputLine);
                    Object[][] table = new Object[rows][8];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < 8; j++) {
                            inputLine = reader.readLine();
                            if (j == 1) {
                                System.out.println(inputLine);
                            }
                            table[i][j] = inputLine;
                        }
                    }
                    mainFrame.getPatientsListPanel().setRows(table);
                    mainFrame.pack();

                } else if (inputLine.equals("Doctor valid")) {
                    mainFrame.getPatientsCrudAndFiltersPanel().sendPatientInfo();
                } else if (inputLine.equals("Doctor invalid")) {
                    MessageFrame messageFrame = new MessageFrame("Doctor inexistent!");
                } else if (inputLine.equals("Programare efectuata")) {
                    MessageFrame messageFrame = new MessageFrame("Programare efectuata cu succes!");
                } else if (inputLine.equals("Programare indisponibila")) {
                    MessageFrame messageFrame = new MessageFrame("Programare indisponibila!");
                    System.out.println(inputLine);
                } else if (inputLine.equals("Pacient inexistent")) {
                    MessageFrame messageFrame = new MessageFrame("Pacient inexistent!");
                } else if (inputLine.equals("Pacient actualizat cu succes")) {
                    MessageFrame messageFrame = new MessageFrame("Pacient actualizat cu succes!");
                } else if (inputLine.equals("Doctor actualizat")) {
                    MessageFrame messageFrame = new MessageFrame("Doctor actualizat cu succes!");
                } else if (inputLine.equals("id doctori")) {
                    System.out.println(inputLine);
                    int numberOfDoctors = Integer.parseInt(reader.readLine());
                    for( int i = 0; i < numberOfDoctors; i++) {
                        inputLine = reader.readLine();
                        System.out.println(inputLine);
                        mainFrame.getPatientsCrudAndFiltersPanel().addDoctor(inputLine);
                    }
                } else {
                    System.out.println(inputLine);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
