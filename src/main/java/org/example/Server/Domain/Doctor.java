package org.example.Server.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa entitate pentru doctori
 */
@Entity
@Table(name = "doctors")
@NamedQueries({
        @NamedQuery(name = "Doctor.findAll", query = "select doctor from Doctor doctor order by doctor.id"),
        @NamedQuery(name = "Doctor.findBySpecializare", query = "select doctor from Doctor doctor where doctor.specializare=?1 order by doctor.id"),
        @NamedQuery(name = "Doctor.findByCabinet", query = "select doctor from Doctor doctor where doctor.cabinet=?1 order by doctor.id"),
        @NamedQuery(name = "Doctor.findBySpecializareAndCabinet", query = "select doctor from Doctor doctor where doctor.specializare = ?1 and doctor.cabinet = ?2 order by doctor.id"),
        @NamedQuery(name = "Doctor.addDoctor", query = "insert into Doctor(nume,prenume,telefon,specializare,cabinet) values (?1,?2,?3,?4,?5)"),
        @NamedQuery(name = "Doctor.deleteDoctor", query = "delete from Doctor doctor where doctor.id = ?1"),
        @NamedQuery(name = "Doctor.updateDoctor", query = "UPDATE Doctor d SET d.nume = ?2, d.prenume = ?3, d.telefon = ?4, d.specializare = ?5, d.cabinet = ?6 WHERE d.id = ?1"),
        @NamedQuery(name = "Doctor.findById", query = "select doctor from Doctor doctor where doctor.id = ?1")
})
public class Doctor {

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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
    @Column(name = "specializare")
    private  String specializare;

    @Basic
    @Column(name = "cabinet")
    private String cabinet;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patientList = new ArrayList<>();

    public Doctor() {

    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public Doctor(int id, String nume, String prenume, String telefon, String specializare, String cabinet) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
        this.specializare = specializare;
        this.cabinet = cabinet;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getSpecializare() {
        return specializare;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }
}
