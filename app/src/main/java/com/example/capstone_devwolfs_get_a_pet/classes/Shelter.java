package com.example.capstone_devwolfs_get_a_pet.classes;

public class Shelter {

    private String shelterId;
    private String shelterName;
    private String shelterEmail;
    private String shelterPhone;
    private String shelterAddress;
    private String shelterDescription;
    private String shelterPassword;

    public Shelter(String shelterId, String shelterName, String shelterEmail, String shelterPhone, String shelterAddress, String shelterDescription, String shelterPassword) {
        this.shelterId = shelterId;
        this.shelterName = shelterName;
        this.shelterEmail = shelterEmail;
        this.shelterPhone = shelterPhone;
        this.shelterAddress = shelterAddress;
        this.shelterDescription = shelterDescription;
        this.shelterPassword = shelterPassword;
    }

    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterEmail() {
        return shelterEmail;
    }

    public void setShelterEmail(String shelterEmail) {
        this.shelterEmail = shelterEmail;
    }

    public String getShelterPhone() {
        return shelterPhone;
    }

    public void setShelterPhone(String shelterPhone) {
        this.shelterPhone = shelterPhone;
    }

    public String getShelterAddress() {
        return shelterAddress;
    }

    public void setShelterAddress(String shelterAddress) {
        this.shelterAddress = shelterAddress;
    }

    public String getShelterDescription() {
        return shelterDescription;
    }

    public void setShelterDescription(String shelterDescription) {
        this.shelterDescription = shelterDescription;
    }

    public String getShelterPassword() {
        return shelterPassword;
    }

    public void setShelterPassword(String shelterPassword) {
        this.shelterPassword = shelterPassword;
    }
}
