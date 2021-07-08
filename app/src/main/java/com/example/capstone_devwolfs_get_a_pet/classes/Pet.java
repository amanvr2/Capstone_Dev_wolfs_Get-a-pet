package com.example.capstone_devwolfs_get_a_pet.classes;

public class Pet {

    private String petname;
    private Shelter shelter;
    private String photoLink;
    private String breed;
    private String type;
    private String size;
    private String description;


    public Pet(String petname, Shelter shelter, String breed, String type, String size, String description){
        this.petname = petname;
        this.shelter = shelter;
        this.breed = breed;
        this.type = type;
        this.size = size;
        this.description = description;
    }



}
