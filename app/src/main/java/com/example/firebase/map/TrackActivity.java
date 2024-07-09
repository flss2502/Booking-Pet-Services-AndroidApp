package com.example.firebase.map;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase.R;
import com.google.android.gms.maps.model.LatLng;

public class TrackActivity extends AppCompatActivity {
    private Button btnTrack;
    private LatLng myShop; // Define your shop's location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        // Initialize your shop's location
        myShop = new LatLng(10.8418, 106.8106); // Replace with your shop's LatLng

        // Initialize views
        btnTrack = findViewById(R.id.btnGetDirection);

        // Set click listener for the button
        btnTrack.setOnClickListener(view -> {
            // Replace with the logic to get currentLocation
            LatLng currentLocation = new LatLng(10.1234, 106.5678); // Replace with actual current location

            // Call method to get directions
            getDirections(myShop, currentLocation);
        });

        // Apply window insets to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            WindowInsetsCompat systemBars = ViewCompat.getRootWindowInsets(v);
            if (systemBars != null) {
                v.setPadding(systemBars.getSystemWindowInsetLeft(), systemBars.getSystemWindowInsetTop(),
                        systemBars.getSystemWindowInsetRight(), systemBars.getSystemWindowInsetBottom());
            }
            return insets;
        });
    }

    // Method to get directions using Google Maps
    private void getDirections(LatLng from, LatLng to) {
        try {
            // Construct URI for Google Maps directions
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + from.latitude + "," + from.longitude + "/" + to.latitude + "," + to.longitude);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps"); // Set package for Google Maps
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent); // Start Google Maps directions
        } catch (ActivityNotFoundException e) {
            // Handle case where Google Maps app is not installed
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent); // Redirect to Google Play Store
        }
    }
}
