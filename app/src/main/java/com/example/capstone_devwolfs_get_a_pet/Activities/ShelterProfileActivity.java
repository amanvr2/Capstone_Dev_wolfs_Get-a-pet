package com.example.capstone_devwolfs_get_a_pet.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShelterProfileActivity extends AppCompatActivity {

    EditText name,email,phone,address,descp,password;

    SharedPreferences sharedPreferences;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("Shelters");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_profile);

        sharedPreferences = getSharedPreferences("Shelter", Context.MODE_PRIVATE);

        name = findViewById(R.id.shelterNameTv);
        email = findViewById(R.id.shelterEmailTv);
        phone = findViewById(R.id.shelterPhoneTv);
        address = findViewById(R.id.shelterAddressTv);
        descp = findViewById(R.id.shelterDescriptionTv);
        password = findViewById(R.id.shelterPasswordTv);


        name.setText(sharedPreferences.getString("Name","Shelter Name"));
        email.setText(sharedPreferences.getString("Email","Shelter Email"));
        phone.setText(sharedPreferences.getString("Phone","Shelter Phone"));
        address.setText(sharedPreferences.getString("Address","Shelter Address"));
        descp.setText(sharedPreferences.getString("Description","Shelter Description"));
        password.setText(sharedPreferences.getString("ShelterID","New Password"));


    }


}