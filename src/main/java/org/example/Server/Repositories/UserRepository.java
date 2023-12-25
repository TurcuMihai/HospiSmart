package org.example.Server.Repositories;

import jakarta.persistence.TypedQuery;
import org.example.Server.DataBase.DataBaseEntity;
import org.example.Server.Domain.User;

import java.util.List;

/**
 * Clasa cu ajutorului careia putem face operatii asupra tabelei cu utilizatori
 */
public class UserRepository {
    /**
     * gaseste utilizatorul in functie de numele de utilizator
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return (User) DataBaseEntity
                .getEntityManager()
                .createNamedQuery("User.findByUsername")
                .setParameter(1, username)
                .getSingleResult();
    }

    /**
     * gaseste utilizatori in functie de id
     *
     * @param id
     * @return
     */
    public User findById(Integer id) {
        return (User) DataBaseEntity
                .getEntityManager()
                .createNamedQuery("User.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * gaseste toti utilizatori din baza de date
     *
     * @return
     */
    public List<User> findAll() {
        TypedQuery<User> query = DataBaseEntity
                .getEntityManager()
                .createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

}
