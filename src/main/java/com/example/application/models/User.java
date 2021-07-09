package com.example.application.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"users"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String lastName;
    private String mail;
    private String companyName;
    private long password;
    @ManyToMany(mappedBy = "users")
    private Set<Company> companies;

    @ManyToOne
    private SystemUser systemUser;


    public Set<Company> getCompanies() {
        return companies=new HashSet<>();
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public User() {
    }

    public User(String name, String lastName, String mail, String companyName, long password) {

        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.companyName = companyName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return
                ", id= " + id +'\n'+
                ", name= " + name + '\n' +
                ", lastName= " + lastName + '\n' +
                ", mail= " + mail + '\n' +
                ", companyName= " + companyName + '\n'
                ;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
