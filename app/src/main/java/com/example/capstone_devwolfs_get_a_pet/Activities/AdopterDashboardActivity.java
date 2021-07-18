package com.example.capstone_devwolfs_get_a_pet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_devwolfs_get_a_pet.Activities.Adopters.FindPetActivity;
import com.example.capstone_devwolfs_get_a_pet.Activities.Adopters.FindShelterActivity;
import com.example.capstone_devwolfs_get_a_pet.InternalData.PersistentData;
import com.example.capstone_devwolfs_get_a_pet.R;
import com.squareup.picasso.Picasso;

public class AdopterDashboardActivity extends AppCompatActivity {

    ImageView AdopterPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_dashboard);

        //Loads adopter Image
        String imageLink = PersistentData.getAdopterImage(getApplicationContext());
        AdopterPhoto = findViewById(R.id.imageViewAdopter);
        Picasso.get().load(imageLink).into(AdopterPhoto);
        Log.d("IMAGE", "onCreate: "+imageLink);

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