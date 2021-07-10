package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShelterProfileActivity extends AppCompatActivity {

    TextView name,email,phone,address,descp,password;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("Shelters");

    String test = ShelterLoginActivity.shelterUsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_profile);

        name = findViewById(R.id.shelterNameTv);
        email = findViewById(R.id.shelterEmailTv);
        phone = findViewById(R.id.shelterPhoneTv);
        address = findViewById(R.id.shelterAddressTv);
        descp = findViewById(R.id.shelterDescriptionTv);
        password = findViewById(R.id.shelterPasswordTv);

        loadData();


    }

    private void loadData(){


        userRef.whereEqualTo("shelterEmail",test).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    String shName = documentSnapshot.getString("shelterName");
                    String shEmail = documentSnapshot.getString("shelterEmail");
                    String shPhone = documentSnapshot.getString("shelterPhone");
                    String shAddress = documentSnapshot.getString("shelterAddress");
                    String shDescp = documentSnapshot.getString("shelterDescription");
                    String shPass = documentSnapshot.getString("shelterPassword");

                    name.setText(shName);
                    email.setText(shEmail);
                    phone.setText(shPhone);
                    address.setText(shAddress);
                    descp.setText(shDescp);
                    password.setText(shPass);

                }


            }
        });

    }
}