package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.capstone_devwolfs_get_a_pet.R;

public class ShelterDashboardActivity extends AppCompatActivity {


    TextView shelterUs;
    String us = ShelterLoginActivity.shelterUsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_dashboard);

        shelterUs = findViewById(R.id.shelterUsname);
        shelterUs.setText(us);

    }
}