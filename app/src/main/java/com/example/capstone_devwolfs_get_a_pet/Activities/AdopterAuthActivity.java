package com.example.capstone_devwolfs_get_a_pet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.example.capstone_devwolfs_get_a_pet.classes.Adopter;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AdopterAuthActivity extends AppCompatActivity {

    EditText aName, aEmail,aPassword,aPhone,aAddress,aDescription;
    TextView alogin;
    Button save;
    ImageView adopterImage;
    Uri adopterImageUri;
    FloatingActionButton fabAdopter;
    Bitmap abitmap;


    public static final String TAG = "test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopter_auth);
        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        aName = findViewById(R.id.adopterName);
        aEmail = findViewById(R.id.adopterEmail);
        aPassword = findViewById(R.id.adopterPassword);
        aDescription = findViewById(R.id.adopterDescription);
        aPhone = findViewById(R.id.adopterPhone);
        aAddress = findViewById(R.id.adopterAddress);

        save = findViewById(R.id.adoptersaveBtn);
        alogin = findViewById(R.id.adopterLoginRedirect);
        adopterImage = findViewById(R.id.profile_adopterImage);
        fabAdopter = findViewById(R.id.adopterProfileImagefloatingActionButton);
        fabAdopter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ImagePicker.with(AdopterAuthActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadtoFireBase();
                /*
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

                 */
            }

            private void uploadtoFireBase() {
                String name = aName.getText().toString().trim();
                String email = aEmail.getText().toString().trim();
                String password = aPassword.getText().toString().trim();
                String phone = aPhone.getText().toString().trim();
                String address = aAddress.getText().toString().trim();
                String description = aDescription.getText().toString().trim();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReferenceFromUrl("gs://capstone-100bc.appspot.com/");
                StorageReference imageName = storageRef.child("adopterProfileImage"+System.currentTimeMillis()+".jpg");


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