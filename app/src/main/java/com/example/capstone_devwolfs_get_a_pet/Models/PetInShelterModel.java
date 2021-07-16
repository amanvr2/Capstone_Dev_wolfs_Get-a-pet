package com.example.capstone_devwolfs_get_a_pet.Models;

public class PetInShelterModel {

    private String petName;
    private String breed;
    private String description;
    private String petImage;
    private String size;
    private String type;


    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPetName(){
        return petName;
    }
    public void setPetName(String petName){
        this.petName = petName;
    }


}
