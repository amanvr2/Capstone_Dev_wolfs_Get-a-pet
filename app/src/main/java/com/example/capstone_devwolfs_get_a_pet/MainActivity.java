package com.example.capstone_devwolfs_get_a_pet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText sName, sEmail,sPassword,sPhone,sAddress,sDescription;
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
}