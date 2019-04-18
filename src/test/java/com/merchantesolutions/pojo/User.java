package com.merchantesolutions.pojo;

public class User {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String name;
    private String email;
    private String username;

    @Override
    public String toString() {
        return "users{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", username=" + username +
                '}';
    }
}
