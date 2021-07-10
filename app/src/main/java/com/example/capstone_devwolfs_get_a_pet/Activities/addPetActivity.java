package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.example.capstone_devwolfs_get_a_pet.classes.Pet;
import com.example.capstone_devwolfs_get_a_pet.classes.Shelter;
import com.google.firebase.firestore.FirebaseFirestore;

public class addPetActivity extends AppCompatActivity {

    EditText pName, pBreed,pType,pDescription,pSize;

    Button save;

    public static final String TAG = "test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        pName = findViewById(R.id.petName);
        pBreed = findViewById(R.id.petBreed);
        pType = findViewById(R.id.petType);
        pDescription = findViewById(R.id.petDescription);
        pSize = findViewById(R.id.petSize);
        save = findViewById(R.id.petSaveBtn);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shelterid = ShelterLoginActivity.shelterUsID;
                String name = pName.getText().toString().trim();
                String breed = pBreed.getText().toString().trim();
                String type = pType.getText().toString().trim();
                String description = pDescription.getText().toString().trim();
                String size = pSize.getText().toString().trim();


                Pet pet = new Pet(name,shelterid,breed,type,size,description);

                db.collection("Pets").add(pet);

                //clearFields();
                Toast.makeText(getApplicationContext(),"Pet Added",Toast.LENGTH_LONG).show();
            }
        });

    }
}