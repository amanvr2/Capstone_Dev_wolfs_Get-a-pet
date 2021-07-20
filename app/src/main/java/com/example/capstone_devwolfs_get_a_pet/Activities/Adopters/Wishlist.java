package com.example.capstone_devwolfs_get_a_pet.Activities.Adopters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_devwolfs_get_a_pet.InternalData.PersistentData;
import com.example.capstone_devwolfs_get_a_pet.Models.PetInShelterModel;
import com.example.capstone_devwolfs_get_a_pet.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


public class Wishlist extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView petsListWishlist;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        firebaseFirestore = FirebaseFirestore.getInstance();
        petsListWishlist = findViewById(R.id.wishlistRecyclerView);

        //Retrieve the wishlist from the user
        String wishlistStr = PersistentData.getAdopterWishlist(this);
        Toast.makeText(getApplicationContext(), wishlistStr, Toast.LENGTH_LONG).show();
        String[] elements = wishlistStr.split("\\s*,\\s*");

        if(wishlistStr.equals("") || wishlistStr == null){
            Toast.makeText(getApplicationContext(), "No pet Found", Toast.LENGTH_LONG).show();
            elements = new String[]{"noPet"};
        }


        //This is the query
        Query query = firebaseFirestore.collection("Pets").whereIn(FieldPath.documentId(),Arrays.asList(elements));


        //This is the code that builds the cells of each pet
        FirestoreRecyclerOptions<PetInShelterModel> options = new FirestoreRecyclerOptions.Builder<PetInShelterModel>()
                .setQuery(query, PetInShelterModel.class)
                .build();

        //Adapter
        adapter =  new FirestoreRecyclerAdapter<PetInShelterModel, PetsViewHolderWishlist>(options) {

            @NonNull
            @NotNull
            @Override
            public PetsViewHolderWishlist onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item,parent, false);
                return new PetsViewHolderWishlist(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull PetsViewHolderWishlist holder, int position, @NonNull @NotNull PetInShelterModel model) {
                holder.petName.setText(model.getPetName());
                Picasso.get().load(model.getPetImage()).into(holder.petPhoto);

               /*

                holder.deletePet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), "Pet Deleted", Toast.LENGTH_LONG).show();

                    }
                });

                */

            }
        };

        petsListWishlist.setHasFixedSize(true);
        petsListWishlist.setLayoutManager(new LinearLayoutManager(this));
        petsListWishlist.setAdapter(adapter);

    }

    //View Holder
    private class PetsViewHolderWishlist extends RecyclerView.ViewHolder {

        private TextView petName;
        private ImageView petPhoto;
        private ImageButton deletePet;

        public PetsViewHolderWishlist(@NonNull @NotNull View itemView) {
            super(itemView);

            petName = itemView.findViewById(R.id.petNameWishList);
            petPhoto = itemView.findViewById(R.id.petImageWishList);
            deletePet = itemView.findViewById(R.id.deleteFromWishlistBtn);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();


    }


}