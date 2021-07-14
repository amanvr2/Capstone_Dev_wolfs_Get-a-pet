package com.example.capstone_devwolfs_get_a_pet.Activities.Adopter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.capstone_devwolfs_get_a_pet.Activities.AdopterDashboardActivity;
import com.example.capstone_devwolfs_get_a_pet.R;

public class FindPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pet);
    }

    public void petDetails(View view) {
        Intent intent = new Intent(FindPetActivity.this, PetDetailsActivity.class);
        startActivity(intent);
    }
}