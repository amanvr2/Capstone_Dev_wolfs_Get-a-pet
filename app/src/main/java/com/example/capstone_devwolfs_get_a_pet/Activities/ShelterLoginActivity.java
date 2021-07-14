package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstone_devwolfs_get_a_pet.InternalData.PersistentData;
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

    public static String shelterUsID = "";
    public static String shelterAddress = "";
    public static String shelterDescription = "";
    public static String shelterEmail = "";
    public static String shelterName = "";
    public static String shelterPhone = "";

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

                        if(queryDocumentSnapshots.size() == 1) {

                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                                 shelterUsID = documentSnapshot.getId();
                                 shelterAddress = documentSnapshot.getString("shelterAddress");
                                 shelterDescription = documentSnapshot.getString("shelterDescription");
                                 shelterEmail = documentSnapshot.getString("shelterEmail");
                                 shelterName = documentSnapshot.getString("shelterName");
                                 shelterPhone = documentSnapshot.getString("shelterPhone");

                                 //saves information on internal storage
                                PersistentData.saveShelterData(shelterUsID,shelterAddress,shelterDescription,shelterEmail,shelterName,shelterPhone,v.getContext());

                                Intent intent = new Intent(v.getContext(), ShelterDashboardActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                            }
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });
    }
}