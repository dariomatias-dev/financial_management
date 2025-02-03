package org.cafe.models.userAccount;

import java.util.Date;

public class UserAccountModel {
    private String name;
    private double telephone;
    private String email;
    private double cpf;
    private Date dataBirth;
    private String password;

    public UserAccountModel(
        String name,
        double telephone,
        String email,
        double cpf,
        Date dataBirth,
        String password
    ) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.cpf = cpf;
        this.dataBirth = dataBirth;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public  double getTelephone() {
        return telephone;
    }

    public String getEmail () {
        return email;
    }

    public  double getCpf () {
        return  cpf;
    }

    public Date getDataBirth() {
        return dataBirth;
    }

    public  String getPassword() {
        return password;
    }

    public void setName(String name){
        this.name = name;
    }

    public  void setTelephone(double telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCpf(double cpf){
        this.cpf = cpf;
    }

    public void setDataBirth(Date dataBirth){
        this.dataBirth = dataBirth;
    }

    public  void setPassword(String password) {
        this.password = password;
    }
}
