package com.example.capstone_devwolfs_get_a_pet.Activities.Shelters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_devwolfs_get_a_pet.Models.PetInShelterModel;
import com.example.capstone_devwolfs_get_a_pet.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

public class ShowAllPetsShelter extends AppCompatActivity {


    SharedPreferences sharedPreferences;

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView petsListShelter;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_pets_shelter);

        sharedPreferences = getSharedPreferences("Shelter", Context.MODE_PRIVATE);

        String thisShelterID = sharedPreferences.getString("ShelterID","");

        firebaseFirestore = FirebaseFirestore.getInstance();
        petsListShelter = findViewById(R.id.petListShelter);

        //This is the query
        Query query = firebaseFirestore.collection("Pets").whereEqualTo("shelterId",thisShelterID);

        //This is the code that builds the cells of each pet
        FirestoreRecyclerOptions<PetInShelterModel> options = new FirestoreRecyclerOptions.Builder<PetInShelterModel>()
                .setQuery(query, PetInShelterModel.class)
                .build();

        //Adapter
        adapter =  new FirestoreRecyclerAdapter<PetInShelterModel, PetsViewHolder>(options) {

            @NonNull
            @NotNull
            @Override
            public PetsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_in_shelter,parent, false);
                return new PetsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull PetsViewHolder holder, int position, @NonNull @NotNull PetInShelterModel model) {
                holder.petName.setText(model.getPetName());
            }
        };

        petsListShelter.setHasFixedSize(true);
        petsListShelter.setLayoutManager(new LinearLayoutManager(this));
        petsListShelter.setAdapter(adapter);

    }

    //View Holder
    private class PetsViewHolder extends RecyclerView.ViewHolder {

        private TextView petName;

        public PetsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            petName = itemView.findViewById(R.id.petNameShelter);

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
