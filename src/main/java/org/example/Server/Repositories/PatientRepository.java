package org.example.Server.Repositories;

import jakarta.persistence.TypedQuery;
import org.example.Server.DataBase.DataBaseEntity;
import org.example.Server.Domain.Doctor;
import org.example.Server.Domain.Patient;

import java.util.List;

/**
 * Clasa cu ajutorului careia putem face operatii asupra tabelei de programari a pacientilor
 */
public class PatientRepository {
    /**
     * gaseste toti pacienti din baza de date
     *
     * @return lista pacientilor existenti
     */
    public List<Patient> findAll() {
        TypedQuery<Patient> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.findAll", Patient.class);
        System.out.println(query);
        return query.getResultList();
    }

    /**
     * actualizeaza date despre un anumit pacient primit ca parametru
     *
     * @param patient
     */
    public synchronized void updatePatient(Patient patient) {
        DataBaseEntity.getEntityManager().getTransaction().begin();
        Doctor doctor = patient.getDoctor();
        Doctor doctorEntity = DataBaseEntity.getEntityManager().getReference(Doctor.class, doctor.getId());

        DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.updatePatient")
                .setParameter(1, patient.getId())
                .setParameter(2, patient.getNume())
                .setParameter(3, patient.getPrenume())
                .setParameter(4, patient.getTelefon())
                .setParameter(5, doctorEntity)
                .setParameter(6, patient.getData())
                .setParameter(7, patient.getOra())
                .executeUpdate();

        DataBaseEntity.getEntityManager().getTransaction().commit();
    }

    /**
     * gaseste un pacient in functie de id
     *
     * @param id
     * @return pacientul cu id-ul dat ca parametru
     */
    public Patient findById(Integer id) {
        TypedQuery<Patient> doctor = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.findById", Patient.class)
                .setParameter(1, id);
        return doctor.getSingleResult();
    }

    /**
     * adauga un pacient in baza de date
     *
     * @param patient
     */
    public void addPatient(Patient patient) {
        DataBaseEntity.getEntityManager().getTransaction().begin();
        DataBaseEntity.getEntityManager().persist(patient);
        DataBaseEntity.getEntityManager().getTransaction().commit();
    }

    /**
     * gaseste un pacient programat la un anumit doctor la o anumita ora si data
     *
     * @param id
     * @param data
     * @param ora
     * @return pacienti care indeplinesc aceste criterii
     */
    public Patient findByDoctorDateAndHour(int id, String data, String ora) {
        TypedQuery<Patient> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.findByDoctorDateAndHour", Patient.class)
                .setParameter(1, id)
                .setParameter(2, data)
                .setParameter(3, ora);
        Patient patient = query.getSingleResult();
        System.out.println(patient.getDoctor().getNume() + " AM GASIT O PROGRAMARE");
        return patient;
    }

    /**
     * gaseste pacientii dupa data la care sunt programati
     *
     * @param data
     * @return
     */
    public List<Patient> findByData(String data) {
        TypedQuery<Patient> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.findByData", Patient.class)
                .setParameter(1, data);
        return query.getResultList();
    }

    /**
     * gaseste pacienti programati la un anumit doctor dat ca parametru
     *
     * @param doctor
     * @return
     */
    public List<Patient> findByDoctor(String doctor) {
        DoctorRepository doctorRepository = new DoctorRepository();
        int doctorId = Integer.parseInt(doctor);
        Doctor findDoctor = doctorRepository.findById(doctorId);
        TypedQuery<Patient> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.findByDoctor", Patient.class)
                .setParameter(1, findDoctor);
        return query.getResultList();
    }

    /**
     * gaseste pacienti programati la o anumita data la un anumit doctor
     *
     * @param doctor
     * @param data
     * @return
     */
    public List<Patient> findByDoctorAndData(String doctor, String data) {
        DoctorRepository doctorRepository = new DoctorRepository();
        int doctorId = Integer.parseInt(doctor);
        Doctor doc = doctorRepository.findById(doctorId);
        System.out.println(doctor);
        System.out.println(data);
        TypedQuery<Patient> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.findByDoctorAndData", Patient.class)
                .setParameter(1, doc)
                .setParameter(2, data);
        return query.getResultList();
    }

    /**
     * sterge programarea unui pacient
     *
     * @param patient
     */
    public void deletePatient(Patient patient) {
        DataBaseEntity.getEntityManager().getTransaction().begin();
        DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.deletePatient")
                .setParameter(1, patient.getId())
                .executeUpdate();
        DataBaseEntity.getEntityManager().getTransaction().commit();
    }

}
