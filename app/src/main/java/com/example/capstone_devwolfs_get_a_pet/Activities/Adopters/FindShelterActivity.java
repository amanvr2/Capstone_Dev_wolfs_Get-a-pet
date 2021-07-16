package com.example.capstone_devwolfs_get_a_pet.Activities.Adopters;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.capstone_devwolfs_get_a_pet.R;
import com.example.capstone_devwolfs_get_a_pet.classes.Shelter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FindShelterActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private static final int REQUEST_CODE = 1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("Shelters");
    private ArrayList<Shelter> shelters = new ArrayList<Shelter>();
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_shelter);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        getShelters();
    }

    private  void getShelters(){
        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    System.out.println(documentSnapshot);
                    Shelter shelter = new Shelter();
                    shelter.setShelterId(documentSnapshot.getId());
                    shelter.setShelterAddress(documentSnapshot.getString("shelterAddress"));
                    shelter.setShelterDescription(documentSnapshot.getString("shelterDescription"));
                    shelter.setShelterEmail(documentSnapshot.getString("shelterEmail"));
                    shelter.setShelterName(documentSnapshot.getString("shelterName"));
                    shelters.add(shelter);
                }

                setShelters();

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //int position = (int)(marker.getTag());
                for (Shelter shlter: shelters) {
                    if (marker.getTitle().equals(shlter.getShelterName())){
                        Intent intent = new Intent(FindShelterActivity.this, ShelterDetailActivity.class);
                        intent.putExtra("shelterName", shlter.getShelterName());
                        intent.putExtra("shelterEmail", shlter.getShelterEmail());
                        intent.putExtra("shelterAddress", shlter.getShelterAddress());
                        intent.putExtra("shelterDes", shlter.getShelterDescription());
                        startActivity(intent);
                        return false;
                    }
                }
                return false;
            }
        });


        if (checkPermission())
            requestPermission();
        else {
            getLocation();

        }
    }

    private boolean checkPermission() {
        int isGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return isGranted != PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (checkPermission())
                requestPermission();
            else {
                getLocation();

            }
        }
    }

    public void getLocation() {
        try {
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult.getLocations().size() > 0){
                        Location location = locationResult.getLocations().get(locationResult.getLocations().size() - 1);
                        setCurrent(new LatLng(location.getLatitude(), location.getLongitude()));
                        fusedLocationClient.removeLocationUpdates(this);
                    }
                }
            };
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(2000);
            mLocationRequest.setFastestInterval(2000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        } catch (Exception e) {
            Log.d("error", e.getMessage());
            e.printStackTrace();
        }

    }

    private void setCurrent(LatLng location) {
        map.clear();
        currentLocation = new Location("myLocation");
        currentLocation.setLatitude(location.latitude);
        currentLocation.setLongitude(location.longitude);
        MarkerOptions markerOptions = new MarkerOptions().position(location)
                .title("Your Location")
                .snippet("You are here");
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 1));
        setShelters();
    }

    private void setShelters(){
        for (Shelter shlter: shelters){
            // Creating a marker
            MarkerOptions markerOptions = new MarkerOptions();

            // Setting the position for the marker


            if (shlter.getShelterAddress().toLowerCase().contains("toronto")){
                markerOptions.position(new LatLng(43.653225,-79.383186));
            }
            else  if (shlter.getShelterAddress().toLowerCase().contains("brampton")){
                markerOptions.position(new LatLng(43.731548,-79.762421));
            }
            else  if (shlter.getShelterAddress().toLowerCase().contains("mississauga")){
                markerOptions.position(new LatLng(43.589046,-79.644119));
            }
            else {
                markerOptions.position(new LatLng(43.2557,-79.871101));
            }
            markerOptions.title(shlter.getShelterName());
            map.addMarker(markerOptions);
        }
    }
}