package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdopterAuthActivity extends AppCompatActivity {

    EditText aName, aEmail,aPassword,aPhone,aAddress,aDescription;
    TextView alogin;
    Button save;


    public static final String TAG = "test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_auth);


        aName = findViewById(R.id.adopterName);
        aEmail = findViewById(R.id.adopterEmail);
        aPassword = findViewById(R.id.adopterPassword);
        aDescription = findViewById(R.id.adopterDescription);
        aPhone = findViewById(R.id.adopterPhone);
        aAddress = findViewById(R.id.adopterAddress);

        save = findViewById(R.id.adoptersaveBtn);
        alogin = findViewById(R.id.adopterLoginRedirect);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}