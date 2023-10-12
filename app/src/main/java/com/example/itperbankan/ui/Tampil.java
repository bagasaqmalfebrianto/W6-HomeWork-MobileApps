package com.example.itperbankan.ui;

public class Tampil {

    private String usernameuser;

    private String password;

    public Tampil(){

    }

    public Tampil(String usernameuser, String password) {
        this.usernameuser = usernameuser;
        this.password = password;
    }

    public String getUsernameuser() {
        return usernameuser;
    }

    public void setUsernameuser(String usernameuser) {
        this.usernameuser = usernameuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
