package org.example.Server.Domain;

import jakarta.persistence.*;

/**
 * Clasa entitate pentru pacienti
 */
@Entity
@Table(name = "patients")
@NamedQueries({
        @NamedQuery(name = "Patient.findAll", query = "select patient from Patient patient order by patient.id"),
        @NamedQuery(name = "Patient.deletePatient", query = "delete from Patient patient where patient.id = ?1"),
        @NamedQuery(name = "Patient.findByDoctorDateAndHour", query = "select patient from Patient patient where patient.doctor.id = ?1 and patient.data = ?2 and patient.ora = ?3"),
        @NamedQuery(name = "Patient.updatePatient", query = "UPDATE Patient p SET p.nume = ?2, p.prenume = ?3, p.telefon = ?4, p.doctor = ?5, p.data = ?6, p.ora = ?7 WHERE p.id = ?1"),
        @NamedQuery(name = "Patient.findById", query = "select patient from Patient patient where patient.id = ?1"),
        @NamedQuery(name = "Patient.deleteByDoctor", query = "delete from Patient patient where patient.doctor = ?1"),
        @NamedQuery(name = "Patient.findByDoctor", query = "select patient from Patient patient where patient.doctor = ?1"),
        @NamedQuery(name = "Patient.findByData", query = "select patient from Patient patient where patient.data = ?1"),
        @NamedQuery(name = "Patient.findByDoctorAndData", query = "select patient from Patient patient where patient.doctor = ?1 and patient.data = ?2")
})
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "nume")
    private String nume;

    @Basic
    @Column(name = "prenume")
    private String prenume;

    @Basic
    @Column(name = "telefon")
    private String telefon;

    @Basic
    @Column(name = "ora")
    private String ora;

    @Basic
    @Column(name = "data")
    private  String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Patient(int id, String nume, String prenume, String telefon, String ora, String data, Doctor doctor) {
        this.id = id;
      //  this.doctorId = doctorId;
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
        this.ora = ora;
        this.data = data;
        this.doctor = doctor;
    }
    public Patient() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
