package com.example.capstone_devwolfs_get_a_pet.Activities.Adopters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.example.capstone_devwolfs_get_a_pet.classes.Shelter;

public class ShelterDetailActivity extends AppCompatActivity {
    EditText name,email,address,descp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);
        name = findViewById(R.id.shelterNameTv);
        email = findViewById(R.id.shelterEmailTv);
        address = findViewById(R.id.shelterAddressTv);
        descp = findViewById(R.id.shelterDescriptionTv);
        String  shelterName = (String) getIntent().getExtras().get("shelterName");
        String  shelterEmail = (String) getIntent().getExtras().get("shelterEmail");
        String  shelterAddress = (String) getIntent().getExtras().get("shelterAddress");
        String  des = (String) getIntent().getExtras().get("shelterDes");
        name.setText(shelterName);
        email.setText(shelterEmail);
        address.setText(shelterAddress);
        descp.setText(des);
    }
}