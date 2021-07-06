package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.capstone_devwolfs_get_a_pet.R;

public class AdopterDashboardActivity extends AppCompatActivity {

    TextView adopterUs;
    String us = AdopterLoginActivity.adopterUsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_dashboard);

        adopterUs = findViewById(R.id.shelterUsname);
        adopterUs.setText(us);

    }
}