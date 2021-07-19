package com.example.capstone_devwolfs_get_a_pet.Activities.Adopters;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class PetDetailsActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    ImageView petImage;
    TextView textPetName, textPetDescription, textBreed, textPetSize;
    TextView textShelterName, textShelterDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        //Retrieve information form your last choice
        String  shelterID = (String) getIntent().getExtras().get("shelterId");
        String  petID = (String) getIntent().getExtras().get("petId");

        //Pet Texts
        textPetName = findViewById(R.id.petNameProfile);
        textPetDescription = findViewById(R.id.petDescProfile);
        textBreed = findViewById(R.id.petBreedProfile);
        textPetSize = findViewById(R.id.petSizeProfile);

        //Shelter Texts
        textShelterName = findViewById(R.id.shelterNamePetProfile);
        textShelterDescription = findViewById(R.id.textShelterDescPetProfile);

        //Images
        petImage = findViewById(R.id.imageViewPetProfile);

        //Database
        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference selectedPet = firebaseFirestore.collection("Pets").document(petID);
        DocumentReference selectedShelter = firebaseFirestore.collection("Shelters").document(shelterID);


        //Gets the information from the Selected pet and writes into the Screen
        selectedPet.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        textPetName.setText(document.getString("petName"));
                        textBreed.setText(document.getString("breed"));
                        textPetSize.setText(document.getString("size"));
                        textPetDescription.setText(document.getString("description"));

                        String imageLink = document.getString("petImage");
                        Picasso.get().load(imageLink).into(petImage);


                    } else {
                        Log.d("DATABASE ERROR", "No such document");
                    }
                } else {
                    Log.d("DATABASE ERROR", "get failed with ", task.getException());
                }


            }
        });

        //Gets the information from the pet's shelter and writes into the Screen
        selectedShelter.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        textShelterName.setText(document.getString("shelterName"));
                        textShelterDescription.setText(document.getString("shelterDescription"));

                    } else {
                        Log.d("DATABASE ERROR", "No such document");
                    }
                } else {
                    Log.d("DATABASE ERROR", "get failed with ", task.getException());
                }

            }
        });



    }
}