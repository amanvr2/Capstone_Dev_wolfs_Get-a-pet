package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.capstone_devwolfs_get_a_pet.R;

public class AdopterDashboardActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_dashboard);

    }

    public void findShelter(View view) {
        Intent intent = new Intent(AdopterDashboardActivity.this, FindShelterActivity.class);
        startActivity(intent);

    }

    public void findPet(View view) {
        Intent intent = new Intent(AdopterDashboardActivity.this, FindPetActivity.class);
        startActivity(intent);
    }
}