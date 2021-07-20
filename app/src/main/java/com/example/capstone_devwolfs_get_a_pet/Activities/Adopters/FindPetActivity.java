package com.example.capstone_devwolfs_get_a_pet.Activities.Adopters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_devwolfs_get_a_pet.Models.PetInShelterModel;
import com.example.capstone_devwolfs_get_a_pet.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class FindPetActivity extends AppCompatActivity {


    private FirebaseFirestore firebaseFirestore;
    private RecyclerView findAllPetsGridRV;
    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pet);

        firebaseFirestore = FirebaseFirestore.getInstance();
        findAllPetsGridRV = findViewById(R.id.gridRecyclerViewFindPets);

        //Query
        Query query = firebaseFirestore.collection("Pets").whereNotEqualTo("petName","No pet found");

        //This is the code that builds the cells of each pet
        FirestoreRecyclerOptions<PetInShelterModel> options = new FirestoreRecyclerOptions.Builder<PetInShelterModel>()
                .setQuery(query, PetInShelterModel.class)
                .build();


        //Adapter
        adapter =  new FirestoreRecyclerAdapter<PetInShelterModel, PetsGridViewHolder>(options) {

            @NonNull
            @NotNull
            @Override
            public PetsGridViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_petgrid,parent, false);
                return new PetsGridViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull PetsGridViewHolder holder, int position, @NonNull @NotNull PetInShelterModel model) {

                Picasso.get().load(model.getPetImage()).into(holder.petPhoto);

                //On Click Photo
                holder.petPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(FindPetActivity.this, PetDetailsActivity.class);
                        intent.putExtra("petId",model.getPetID());
                        intent.putExtra("shelterId", model.getShelterId());
                        startActivity(intent);

                    }
                });

            }
        };

        findAllPetsGridRV.setHasFixedSize(true);
        findAllPetsGridRV.setLayoutManager(new GridLayoutManager(this,3));
        findAllPetsGridRV.setAdapter(adapter);

    }

    //View Holder
    private class PetsGridViewHolder extends RecyclerView.ViewHolder {

        private ImageView petPhoto;

        public PetsGridViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            petPhoto = itemView.findViewById(R.id.squareImagePet);

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