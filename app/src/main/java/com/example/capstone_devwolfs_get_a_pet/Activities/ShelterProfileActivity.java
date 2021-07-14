package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShelterProfileActivity extends AppCompatActivity {

    EditText name,email,phone,address,descp,password;
    ImageView addShelterImage;
    FloatingActionButton fab;
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
        addShelterImage = findViewById(R.id.shelterImage);
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ImagePicker.with(ShelterProfileActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });



        name.setText(sharedPreferences.getString("Name","Shelter Name"));
        email.setText(sharedPreferences.getString("Email","Shelter Email"));
        phone.setText(sharedPreferences.getString("Phone","Shelter Phone"));
        address.setText(sharedPreferences.getString("Address","Shelter Address"));
        descp.setText(sharedPreferences.getString("Description","Shelter Description"));
        password.setText(sharedPreferences.getString("Password","New Password"));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        addShelterImage.setImageURI(uri);
    }
}