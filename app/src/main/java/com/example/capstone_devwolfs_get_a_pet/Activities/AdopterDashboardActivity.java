package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstone_devwolfs_get_a_pet.R;

public class AdopterDashboardActivity extends AppCompatActivity {


    Button wishlistBtn,profileBnt,sheltersBtn,donationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_dashboard);

        wishlistBtn = findViewById(R.id.wishlistBtn);
        profileBnt = findViewById(R.id.profileBtn);
        sheltersBtn = findViewById(R.id.shelterBtn);
        donationBtn = findViewById(R.id.donationBtn);

    }

    public void profileBtnClick(View view) {
        Intent intent = new Intent(view.getContext(), AdopterProfileActivity.class);
        startActivity(intent);
    }
}