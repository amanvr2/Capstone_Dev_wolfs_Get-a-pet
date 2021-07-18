package com.example.capstone_devwolfs_get_a_pet.InternalData;

import android.content.Context;
import android.content.SharedPreferences;

public class PersistentData {

    public static SharedPreferences sharedpreferences;
    public static final String SavedAdopter = "Adopter";
    public static final String SavedShelter = "Shelter";

    public static final String DefaultAdopterImage = "https://firebasestorage.googleapis.com/v0/b/capstone-100bc.appspot.com/o/adopter.png?alt=media&token=3094aa35-ca2e-4d04-977e-a537680a78e6";


    //Saving Adopter data inside the internal storage of the phone
    public static void saveAdopterData(String UserID, String Address, String Description, String email, String name, String phone,String photo, Context context){

        sharedpreferences = context.getSharedPreferences(SavedAdopter, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("LoginStatus", "logged");
        editor.putString("UserID",UserID);
        editor.putString("Address",Address);
        editor.putString("Description",Description);
        editor.putString("Email",email);
        editor.putString("Name", name);
        editor.putString("Phone", phone);
        editor.putString("Photo", photo);

        editor.commit();

    }

    public static String getAdopterImage(Context context){

        sharedpreferences = context.getSharedPreferences(SavedAdopter, Context.MODE_PRIVATE);

        return sharedpreferences.getString("Photo",DefaultAdopterImage);

    }

    //Saving Shelter data inside the internal storage of the phone
    public static void saveShelterData(String ShelterID, String Address, String Description, String email, String name, String phone, Context context){

        sharedpreferences = context.getSharedPreferences(SavedShelter, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("LoginStatus", "logged");
        editor.putString("ShelterID", ShelterID);
        editor.putString("Address",Address);
        editor.putString("Description",Description);
        editor.putString("Email",email);
        editor.putString("Name", name);
        editor.putString("Phone", phone);

        editor.commit();

    }


}
