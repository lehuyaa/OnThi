package com.ldh.onthidaihoc.model;

public class User {
    private String userName;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String score;
    private String image;


    public User() {
    }

    public User(String userName, String name, String password, String phone, String email, String score, String image) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.score = score;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
