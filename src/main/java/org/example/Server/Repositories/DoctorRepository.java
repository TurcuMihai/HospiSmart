package org.example.Server.Repositories;

import jakarta.persistence.TypedQuery;
import org.example.Server.DataBase.DataBaseEntity;
import org.example.Server.Domain.Doctor;
import java.util.List;

/**
 * Clasa cu ajutorului careia putem face operatii asupra tabelei de doctori
 */
public class DoctorRepository {
    /**
     * gaseste toti doctorii din baza de date
     *
     * @return lista doctorilor existenti
     */
    public List<Doctor> findAll() {
        TypedQuery<Doctor> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.findAll", Doctor.class);
        System.out.println(query);
        return query.getResultList();
    }

    /**
     * adauga un doctor in baza de date
     *
     * @param doctor doctorul care este adaugat
     */
    public void addDoctor(Doctor doctor) {
        DataBaseEntity.getEntityManager().getTransaction().begin();
        DataBaseEntity.getEntityManager().persist(doctor);
        DataBaseEntity.getEntityManager().getTransaction().commit();
    }

    /**
     * sterge un doctor din baza de date
     *
     * @param doctor doctorul care va fi sters
     */
    public void deleteDoctor(Doctor doctor) {
        DataBaseEntity.getEntityManager().getTransaction().begin();
        DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Patient.deleteByDoctor")
                .setParameter(1, doctor)
                .executeUpdate();

        DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.deleteDoctor")
                .setParameter(1, doctor.getId())
                .executeUpdate();
        DataBaseEntity.getEntityManager().getTransaction().commit();
    }

    /**
     * gaseste doctorul in functie de id
     *
     * @param id
     * @return doctorul cu id-ul dat ca parametru
     */
    public Doctor findById(int id) {
        TypedQuery<Doctor> doctor = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.findById", Doctor.class)
                .setParameter(1, id);
        return doctor.getSingleResult();
    }

    /**
     * actualizeaza datele despre un anumit doctor
     *
     * @param doctor doctorului ale carui date vor fi actualizate
     */
    public synchronized void updateDoctor(Doctor doctor) {
        DataBaseEntity.getEntityManager().getTransaction().begin();
        DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.updateDoctor")
                .setParameter(1, doctor.getId())
                .setParameter(2, doctor.getNume())
                .setParameter(3, doctor.getPrenume())
                .setParameter(4, doctor.getTelefon())
                .setParameter(5, doctor.getSpecializare())
                .setParameter(6, doctor.getCabinet())
                .executeUpdate();
        DataBaseEntity
                .getEntityManager()
                .getTransaction()
                .commit();
    }

    /**
     * gaseste doctorii cu o anumita specializare
     *
     * @param specializare
     * @return doctorii cu specializarea data ca parametru
     */
    public List<Doctor> findBySpecializare(String specializare) {
        TypedQuery<Doctor> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.findBySpecializare", Doctor.class)
                .setParameter(1, specializare);
        return query.getResultList();
    }

    /**
     * gaseste doctorii care lucreaza la un anumit cabinet
     *
     * @param cabinet
     * @return doctorii de la cabinetul dat ca parametru
     */
    public List<Doctor> findByCabinet(String cabinet) {
        TypedQuery<Doctor> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.findByCabinet", Doctor.class)
                .setParameter(1, cabinet);
        return query.getResultList();
    }

    /**
     * gaseste doctori in functie de cabinet si specializare
     *
     * @param specializare
     * @param cabinet
     * @return lista doctorilor care au cabinetul si specializarea date ca parametri
     */
    public List<Doctor> findBySpecializareAndCabinet(String specializare, String cabinet) {
        TypedQuery<Doctor> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("Doctor.findBySpecializareAndCabinet", Doctor.class)
                .setParameter(1, specializare)
                .setParameter(2, cabinet);
        return query.getResultList();
    }
}
