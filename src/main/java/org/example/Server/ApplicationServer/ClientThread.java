package org.example.Server.ApplicationServer;

import jakarta.persistence.NoResultException;
import org.example.Server.DataBase.DataBaseEntity;
import org.example.Server.Domain.Doctor;
import org.example.Server.Domain.Patient;
import org.example.Server.Domain.User;
import org.example.Server.Repositories.DoctorRepository;
import org.example.Server.Repositories.PatientRepository;
import org.example.Server.Repositories.UserRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * clasa cu ajutorul careia serverul comunica cu clientul
 */
public class ClientThread extends Thread {
    private Socket clientSocket;

    private ApplicationServer applicationServer;

    private BufferedReader input;
    private PrintWriter output;
    private String inputLine;

    /**
     * constructor pentru obiecte de timp ClientThread
     *
     * @param clientSocket
     * @param applicationServer
     */
    public ClientThread(Socket clientSocket, ApplicationServer applicationServer) {
        this.clientSocket = clientSocket;
        this.applicationServer = applicationServer;
    }

    /**
     * preia datele unui doctor care urmeaza sa fie inserat si solicita actiunea
     *
     * @throws IOException
     */
    public synchronized void insertDoctorRequest() throws IOException {
        Doctor doctor = new Doctor();
        inputLine = input.readLine();
        doctor.setNume(inputLine);
        inputLine = input.readLine();
        doctor.setPrenume(inputLine);
        inputLine = input.readLine();
        doctor.setTelefon(inputLine);
        inputLine = input.readLine();
        doctor.setSpecializare(inputLine);
        inputLine = input.readLine();
        doctor.setCabinet(inputLine);

        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.addDoctor(doctor);
        output.println("comanda executata cu succes doctori");
    }

    /**
     * preia datele necesare stergerii unui doctor si solicita actiunea
     *
     * @throws IOException
     */
    public synchronized void deleteDoctorRequest() throws IOException {
        Doctor doctor = new Doctor();
        inputLine = input.readLine();
        doctor.setId(Integer.parseInt(inputLine));

        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.deleteDoctor(doctor);
        output.println("comanda executata cu succes doctori");
    }

    /**
     * preia datele necesare stergerii unui pacient si solicita actiunea
     *
     * @throws IOException
     */
    public synchronized void deletePatientRequest() throws IOException {
        Patient patient = new Patient();
        inputLine = input.readLine();
        patient.setId(Integer.parseInt(inputLine));

        PatientRepository patientRepository = new PatientRepository();
        patientRepository.deletePatient(patient);
        output.println("comanda executata cu succes pacienti");
    }

    /**
     * preia datele necesare actualizarii unui doctor si solicita actiunea
     *
     * @throws IOException
     */
    public synchronized void updateDoctorRequest() throws IOException {
        Doctor doctor = new Doctor();
        inputLine = input.readLine();
        doctor.setId(Integer.parseInt(inputLine));
        inputLine = input.readLine();
        doctor.setNume(inputLine);
        inputLine = input.readLine();
        doctor.setPrenume(inputLine);
        inputLine = input.readLine();
        doctor.setTelefon(inputLine);
        inputLine = input.readLine();
        doctor.setSpecializare(inputLine);
        inputLine = input.readLine();
        doctor.setCabinet(inputLine);

        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.updateDoctor(doctor);
        output.println("Doctor actualizat");
        output.println("comanda executata cu succes doctori");
    }

    /**
     * preia datele necesare actualizarii unei programari a unui pacient si solicita actiunea
     *
     * @throws IOException
     */
    public synchronized void updatePatientRequest() throws IOException {
        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();

        try {
            Patient patient = new Patient();
            inputLine = input.readLine();
            patient.setId(Integer.parseInt(inputLine));
            Patient existPatient = patientRepository.findById(patient.getId());
            inputLine = input.readLine();
            patient.setNume(inputLine);
            inputLine = input.readLine();
            patient.setPrenume(inputLine);
            inputLine = input.readLine();
            patient.setTelefon(inputLine);
            inputLine = input.readLine();
            int doctor_id = Integer.parseInt(inputLine);
            inputLine = input.readLine();
            patient.setData(inputLine);
            inputLine = input.readLine();
            patient.setOra(inputLine);
            System.out.println(patient.getId());
            System.out.println(patient.getNume());
            System.out.println(patient.getData());
            System.out.println(patient.getOra());
            try {
                Doctor doctor = doctorRepository.findById(doctor_id);
                patient.setDoctor(doctor);
                patientRepository.updatePatient(patient);
                System.out.println("am ajuns aici");
                output.println("Pacient actualizat cu succes");
                output.println("comanda executata cu succes pacienti");
            } catch (NoResultException e) {
                output.println("Doctor invalid");
            }
        } catch (NoResultException e) {
            output.println("Pacient inexistent");
        }


    }

    /**
     * preia datele necesare conectarii unui utilizator si solicita identificarea parolei acestuia
     *
     * @throws IOException
     */
    public void loginRequest() throws IOException {
        User userRequest = new User();
        inputLine = input.readLine();
        userRequest.setUsername(inputLine);
        inputLine = input.readLine();
        userRequest.setPassword(inputLine);

        try {
            UserRepository userRepository = new UserRepository();
            User userFound;
            userFound = userRepository.findByUsername(userRequest.getUsername());

            if (userRequest.getUsername().equals(userFound.getUsername()) && userRequest.getPassword().equals(userFound.getPassword())) {
                output.println("Connected");
            } else {
                output.println("Not connected");
            }
        } catch (NoResultException e) {
            output.println("Not connected");
        }

    }

    /**
     * trimite datele necesare despre pacienti in functie de cerere
     *
     * @throws IOException
     */
    public void sendPatientsRequest() throws IOException {
        System.out.println(inputLine);
        String doctor = input.readLine();
        String data = input.readLine();
        PatientRepository repository = new PatientRepository();
        DoctorRepository doctorRepository = new DoctorRepository();

        System.out.println(doctor);
        System.out.println(data);

        List<Patient> patientList;

        DataBaseEntity.setEntityManager();


        if (doctor.equals("Toate") && data.equals("Toate")) {
            patientList = repository.findAll();
        } else if (doctor.equals("Toate")) {
            patientList = repository.findByData(data);
        } else if (data.equals("Toate")) {
            patientList = repository.findByDoctor(doctor);
        } else {
            patientList = repository.findByDoctorAndData(doctor, data);
        }
        output.println("pacienti");
        output.println(patientList.size());

        for (Patient pat : patientList) {
            output.println(pat.getId());
            output.println(pat.getNume());
            output.println(pat.getPrenume());
            output.println(pat.getTelefon());
            output.println(pat.getDoctor().getId());
            output.println(pat.getDoctor().getNume());
            output.println(pat.getData());
            output.println(pat.getOra());
        }
        DataBaseEntity.setEntityManager();

        System.out.println("Am trimis");
    }

    /**
     * preia datele necesare unei programari si verifica daca se poate face programarea
     *
     * @throws IOException
     */
    public synchronized void insertPatientRequest() throws IOException {
        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();

        int id = Integer.parseInt(input.readLine());
        try {
            Doctor doctor = doctorRepository.findById(id);
            output.println("Doctor valid");

            Patient patient = new Patient();
            inputLine = input.readLine();
            patient.setNume(inputLine);
            inputLine = input.readLine();
            patient.setPrenume(inputLine);
            inputLine = input.readLine();
            patient.setTelefon(inputLine);
            patient.setDoctor(doctor);
            inputLine = input.readLine();
            patient.setOra(inputLine);
            inputLine = input.readLine();
            patient.setData(inputLine);

            try {
                Patient sameProgram = patientRepository.findByDoctorDateAndHour(patient.getDoctor().getId(), patient.getData(), patient.getOra());
                output.println("Programare indisponibila");
                System.out.println("Programare indisponibila");
            } catch (NoResultException ex) {
                patientRepository.addPatient(patient);
                output.println("Programare efectuata");
                output.println("comanda executata cu succes pacienti");
                System.out.println("Programare efectuata");
            }
        } catch (NoResultException ex) {
            output.println("Doctor invalid");
        }

    }

    /**
     * trimite date despre doctori in functie de cerere
     *
     * @throws IOException
     */
    public void sendDoctorsRequest() throws IOException {

        System.out.println(inputLine);
        String specializare = input.readLine();
        String cabinet = input.readLine();

        System.out.println(specializare);
        System.out.println(cabinet);

        DoctorRepository repository = new DoctorRepository();
        List<Doctor> doctorList;

        if (cabinet.equals("Toate") && specializare.equals("Toate")) {

            doctorList = repository.findAll();
            System.out.println(doctorList.size());

        } else if (cabinet.equals("Toate")) {
            doctorList = repository.findBySpecializare(specializare);

        } else if (specializare.equals("Toate")) {
            doctorList = repository.findByCabinet(cabinet);
        } else {
            doctorList = repository.findBySpecializareAndCabinet(specializare, cabinet);
        }
        output.println("doctori");
        output.println(doctorList.size());

        for (Doctor doc : doctorList) {
            output.println(doc.getId());
            output.println(doc.getNume());
            output.println(doc.getPrenume());
            output.println(doc.getTelefon());
            output.println(doc.getSpecializare());
            output.println(doc.getCabinet());
        }
        DataBaseEntity.setEntityManager();

        System.out.println("Am trimis");

    }

    /**
     * trimite lista id-urilor doctorilor
     */
    public void idDoctoriRequest() {
        DoctorRepository doctorRepository = new DoctorRepository();
        List<Doctor> doctorList = doctorRepository.findAll();
        output.println("id doctori");
        output.println(doctorList.size());
        for (Doctor doc : doctorList) {
            System.out.println(doc.getId());
            output.println(doc.getId());
        }
    }

    /**
     * asigura comunicarea cu clientul si il serveste in functie de actiunile solicitate de acesta
     */
    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            while ((inputLine = input.readLine()) != null) {
                System.out.println(inputLine + " ESTE COAMANDA PRIMITA");
                if (inputLine.equals("login")) {
                    loginRequest();
                } else if (inputLine.equals("exit")) {
                    return;
                } else if (inputLine.equals("inserare doctor")) {
                    insertDoctorRequest();
                } else if (inputLine.equals("stergere doctor")) {
                    deleteDoctorRequest();
                } else if (inputLine.equals("actualizare doctor")) {
                    updateDoctorRequest();
                } else if (inputLine.startsWith("doctori")) {
                    sendDoctorsRequest();
                } else if (inputLine.equals("stergere pacient")) {
                    deletePatientRequest();
                } else if (inputLine.startsWith("pacienti")) {
                    sendPatientsRequest();
                } else if (inputLine.equals("inserare pacient")) {
                    insertPatientRequest();
                } else if (inputLine.equals("actualizare pacient")) {
                    updatePatientRequest();
                } else if (inputLine.equals("id doctori")) {
                    idDoctoriRequest();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
