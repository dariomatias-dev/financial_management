package org.cafe.models;

import java.util.Date;

public class UserAccountModel {
    private String id;
    private String name;
    private double telephone;
    private String email;
    private double cpf;
    private Date dataBirth;
    private String password;

    public UserAccountModel(
        String id,
        String name,
        double telephone,
        String email,
        double cpf,
        Date dataBirth,
        String password
    ) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.cpf = cpf;
        this.dataBirth = dataBirth;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public  double getTelephone() {
        return telephone;
    }

    public  void setTelephone(double telephone) {
        this.telephone = telephone;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public  double getCpf () {
        return  cpf;
    }

    public void setCpf(double cpf){
        this.cpf = cpf;
    }

    public Date getDataBirth() {
        return dataBirth;
    }

    public void setDataBirth(Date dataBirth){
        this.dataBirth = dataBirth;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  String getPassword() {
        return password;
    }
}
