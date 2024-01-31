package com.example.springboot.models.auth;

import com.example.springboot.models.SingleObjectModel;

public class UserModel implements SingleObjectModel {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int id;

    public UserModel(String firstName, String lastName, String username, String email, String password, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
    }
    public UserModel() {

    }

    public UserModel(String firstName, String lastName, String username, String email, String password) {
        this(firstName, lastName, username, email, password, -1);
    }

    @Override
    public int getId() {
        return id;
    }
}
