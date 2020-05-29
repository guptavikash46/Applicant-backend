package Hahn.ApplicationProcess.Aplication.Aufgabe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    private String familyName;
    private String address;
    private String countryOfOrigin;
    private String emailAddress;
    private Integer age;
    private Boolean hired;

    public Applicant(){}

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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isHired() {
        return hired;
    }

    public void setHired(Boolean hired) {
        this.hired = hired;
    }

    public Applicant(String name, String familyName, String address, String countryOfOrigin, String emailAddress,
            Integer age, Boolean hired) {
        this.name = name;
        this.familyName = familyName;
        this.address = address;
        this.countryOfOrigin = countryOfOrigin;
        this.emailAddress = emailAddress;
        this.age = age;
        this.hired = hired;
    }

    public Applicant(int id, String name, String familyName, String address, String countryOfOrigin,
            String emailAddress, Integer age, Boolean hired) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.address = address;
        this.countryOfOrigin = countryOfOrigin;
        this.emailAddress = emailAddress;
        this.age = age;
        this.hired = hired;
    }
    

    
}