package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstone_devwolfs_get_a_pet.R;

public class ShelterDashboardActivity extends AppCompatActivity {


    Button addPet,viewProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_dashboard);

        addPet = findViewById(R.id.addPetRedirect);
        viewProfile = findViewById(R.id.viewProfileBtn);


        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), addPetActivity.class);
                startActivity(intent);
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShelterProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}