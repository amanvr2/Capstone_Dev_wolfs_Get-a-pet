package com.example.capstone_devwolfs_get_a_pet.Activities.Adopters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_devwolfs_get_a_pet.InternalData.PersistentData;
import com.example.capstone_devwolfs_get_a_pet.Models.PetInShelterModel;
import com.example.capstone_devwolfs_get_a_pet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Wishlist extends AppCompatActivity {

    ListView wishListView;
    private FirebaseFirestore firebaseFirestore;
    public ArrayList<String> wishlistArrayList;
    public ArrayList<PetInShelterModel> petsArrayList;
    DocumentReference user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        wishListView = findViewById(R.id.petWishList);
        petsArrayList = new ArrayList<>();


        //Retrieve information from the user in PersistentData
        String userID = PersistentData.getAdopterId(getApplicationContext());


        firebaseFirestore = FirebaseFirestore.getInstance();
        user = firebaseFirestore.collection("Adopters").document(userID);

        //Get the list of the Wishlist
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        if(document.contains("wishlist")){

                            String wishlist = document.getString("wishlist");

                            //Converting the String into a Arraylist<String>
                            String[] wishlistArray = wishlist.split(",");
                            List<String> listWishlist = Arrays.asList(wishlistArray);
                            wishlistArrayList = new ArrayList<String>(listWishlist);


                            int size = wishlistArrayList.size();
                            String result = String.valueOf(size);
                            Toast.makeText(getApplicationContext(),result , Toast.LENGTH_LONG).show();

                            WishlistAdapter adapter = new WishlistAdapter(getApplicationContext(), R.layout.wishlist_item, wishlistObjects(wishlistArrayList));
                            wishListView.setAdapter(adapter);


                        }else{

                            Toast.makeText(getApplicationContext(), "There are no pets on your wishlist", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Log.d("DATABASE ERROR", "No such document");
                        Toast.makeText(getApplicationContext(), "User not Found", Toast.LENGTH_LONG).show();
                        return;
                    }
                } else {
                    Log.d("DATABASE ERROR", "get failed with ", task.getException());
                    Toast.makeText(getApplicationContext(), "Connection Problem, check your internet", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });




        //Loads every Pet inside the wishlist and stores into and Arraylist




    }

    //Loads every Pet inside the wishlist and stores into and Arraylist
    public ArrayList<PetInShelterModel> wishlistObjects(ArrayList<String> petsFinalList){

        for (String petId : petsFinalList){

            DocumentReference selectedPet = firebaseFirestore.collection("Pets").document(petId);
            selectedPet.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            petsArrayList.add(new PetInShelterModel(
                                    document.getId(),
                                    document.getString("petName"),
                                    document.getString("breed"),
                                    document.getString("description"),
                                    document.getString("petImage"),
                                    document.getString("size"),
                                    document.getString("type"),
                                    document.getString("shelterId")
                            ));



                        } else {
                            Log.d("DATABASE ERROR", "pet not found");
                            return;
                        }
                    } else {
                        Log.d("DATABASE ERROR", "get failed with ", task.getException());
                        Toast.makeText(getApplicationContext(), "Connection Problem, check your connection", Toast.LENGTH_LONG).show();
                        return;
                    }

                }
            });

        }

        return petsArrayList;

    }

    

    private class WishlistAdapter extends ArrayAdapter<PetInShelterModel> {

        private static final String wishTAG = "wishlistAdapter";

        private Context mContext;
        int mResource;

        public WishlistAdapter(@NonNull @NotNull Context context, int resource, @NonNull @NotNull ArrayList<PetInShelterModel> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            String petNameWished = getItem(position).getPetName();
            String imageLinkWished = getItem(position).getPetImage();

            TextView petNameTV;
            ImageView petImageIV;
            ImageButton deletePetIBV;

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(mResource, parent, false);

            petNameTV = findViewById(R.id.petNameWishList);
            petImageIV = findViewById(R.id.petImageWishList);
            deletePetIBV = findViewById(R.id.deleteFromWishlistBtn);

            petNameTV.setText(petNameWished);
            Picasso.get().load(imageLinkWished).into(petImageIV);

            return super.getView(position, convertView, parent);
        }
    }



}