package com.example.capstone_devwolfs_get_a_pet.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_devwolfs_get_a_pet.R;

public class AdopterProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText adopterName,adopterEmail,adopterPhone,adopterAddress,adopterDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_profile);

        sharedPreferences = getSharedPreferences("Adopter",  Context.MODE_PRIVATE);

        adopterName = findViewById(R.id.adopterNameTv);
        adopterEmail = findViewById(R.id.adopterEmailTv);
        adopterPhone = findViewById(R.id.adopterPhoneTv);
        adopterAddress = findViewById(R.id.adopterAddressTv);
        adopterDescription = findViewById(R.id.adopterDescriptionTv);



        adopterName.setText(sharedPreferences.getString("Name","Adopter Name"));
        adopterEmail.setText(sharedPreferences.getString("Email","Adopter Email"));
        adopterPhone.setText(sharedPreferences.getString("Phone","Adopter Phone"));
        adopterAddress.setText(sharedPreferences.getString("Address","Adopter Address"));
        adopterDescription.setText(sharedPreferences.getString("Description","Adopter Description"));

    }
}