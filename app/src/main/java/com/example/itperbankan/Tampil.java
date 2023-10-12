package com.example.itperbankan;

public class Tampil {

    private String usernameuser;

    private Integer saldo;

    public Tampil(){

    }

    public Tampil(String usernameuser, Integer saldo) {
        this.usernameuser = usernameuser;
        this.saldo = saldo;
    }

    public String getUsernameuser() {
        return usernameuser;
    }

    public void setUsernameuser(String usernameuser) {
        this.usernameuser = usernameuser;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
}
