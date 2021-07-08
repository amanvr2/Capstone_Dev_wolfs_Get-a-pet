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
import com.example.capstone_devwolfs_get_a_pet.classes.Adopter;
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
                String name = aName.getText().toString().trim();
                String email = aEmail.getText().toString().trim();
                String password = aPassword.getText().toString().trim();
                String phone = aPhone.getText().toString().trim();
                String address = aAddress.getText().toString().trim();
                String description = aDescription.getText().toString().trim();

                Adopter adopter = new Adopter(name,email,phone,address,description,password);

                db.collection("Adopters").add(adopter);
                clearFields();
                Toast.makeText(getApplicationContext(),"Adopter Added",Toast.LENGTH_LONG).show();
            }
        });

        alogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AdopterLoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void clearFields(){

        aName.setText("");
        aEmail.setText("");
        aPhone.setText("");
        aAddress.setText("");
        aDescription.setText("");
        aPassword.setText("");
    }
}