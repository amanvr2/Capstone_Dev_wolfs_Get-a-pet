package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.example.capstone_devwolfs_get_a_pet.classes.Shelter;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText sName, sEmail,sPassword,sPhone,sAddress,sDescription;
    TextView login;
    Button save;

    public static final String TAG = "test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sName = findViewById(R.id.shelterName);
        sEmail = findViewById(R.id.shelterEmail);
        sPassword = findViewById(R.id.shelterPassword);
        sDescription = findViewById(R.id.shelterDescription);
        sPhone = findViewById(R.id.shelterPhone);
        sAddress = findViewById(R.id.shelterAddress);

        save = findViewById(R.id.shelterSaveBtn);
        login = findViewById(R.id.loginRedirect);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = sName.getText().toString().trim();
                String email = sEmail.getText().toString().trim();
                String password = sPassword.getText().toString().trim();
                String phone = sPhone.getText().toString().trim();
                String address = sAddress.getText().toString().trim();
                String description = sDescription.getText().toString().trim();

                Shelter shelter = new Shelter(name,email,Integer.parseInt(phone),address,description,password);

                db.collection("Shelters").add(shelter);
                Toast.makeText(getApplicationContext(),"Shelter Added",Toast.LENGTH_LONG).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), ShelterLoginActivity.class);
                startActivity(intent);
            }
        });


    }
}