package org.example.Server.DataBase;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton ce realizeaza conexiunea cu baza de date
 */
public class DataBaseEntity {

    private static EntityManagerFactory entityManagerFactory = null;

    private static EntityManager entityManager = null;

    private DataBaseEntity () {

    }

    /**
     * inchide conexiunea cu baza de date
     */
    public static void setEntityManager() {
        DataBaseEntity.entityManager = null;
    }

    /**
     * returneaza conexiunea cu baza de date daca aceasta exista; daca nu exista, creeaza una
     * @return
     */
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("x");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return  entityManager;
    }
}
