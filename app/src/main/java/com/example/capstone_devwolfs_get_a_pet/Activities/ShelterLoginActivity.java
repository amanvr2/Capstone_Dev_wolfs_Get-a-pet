package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.example.capstone_devwolfs_get_a_pet.classes.Shelter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShelterLoginActivity extends AppCompatActivity {

    EditText userName,password;
    Button login;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("Shelters");

    public static String shelterUsName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_login);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.shelterLoginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userName.getText().toString().trim();
                String passWord = password.getText().toString().trim();


                userRef.whereEqualTo("shelterEmail",username).whereEqualTo("shelterPassword",passWord).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){


                            shelterUsName = documentSnapshot.getString("shelterEmail");
                            Intent intent = new Intent(v.getContext(), ShelterDashboardActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();

                        }


                    }
                });


            }
        });
    }
}