package org.example.Server.Domain;

import jakarta.persistence.*;

/**
 * Clasa entitate pentru utilizatori
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "select user from User user where user.username = ?1"),
        @NamedQuery(name = "User.findById", query = "select user from User user where user.id = ?1"),
        @NamedQuery(name = "User.findAll", query = "select user from User user order by user.username")
})
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;


    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
