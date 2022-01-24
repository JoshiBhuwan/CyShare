package com.example.cyshare_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.cyshare_frontend.model.backend_io;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class driver_splash extends AppCompatActivity {
    /**
     * From google. DO NOT CHANGE
     */
    private final String api_key = "AIzaSyDAG5lXDl-7YN46opmBky5L571-iSLxm_Y";
    /**
     * Volley thread for network activity
     */
    public RequestQueue volleyQueue;
    /**
     * Client side maps request
     */
    private PlacesClient placesClient;
    private Button toLobby;
    private String userName;
    private double driverLocLat = 0;
    private double driverLocLng = 0;
    private double driverDestLat = 0;
    private double driverDestLng = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_splash);

        // Get bundle info
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userName = extras.getString("USERNAME");

        // init button
        toLobby = findViewById(R.id.button_to_lobby);

        // Initialize the SDK
        Places.initialize(getApplicationContext(), api_key);
        // Create a new PlacesClient instance
        placesClient = Places.createClient(this);

        // get server data
        volleyQueue = Volley.newRequestQueue(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment driverDestination = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_destination);
        AutocompleteSupportFragment driverLocation = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_location);

        // Specify the types of place data to return.
        driverDestination.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME));
        driverLocation.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        driverDestination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng = place.getLatLng();
                driverDestLat = latLng.latitude;
                driverDestLng = latLng.longitude;
                Log.i("PlacesApi", "Place: " + latLng.latitude + "\n" + latLng.longitude);
            }
            @Override
            public void onError(@NonNull Status status) {
                Log.i("PlacesError", "An error occurred: " + status);
            }
        });
        driverLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng = place.getLatLng();
                driverLocLat = latLng.latitude;
                driverLocLng = latLng.longitude;
                Log.i("PlacesApi", "Place: " + latLng.latitude + "\n" + latLng.longitude);
            }
            @Override
            public void onError(@NonNull Status status) {
                Log.i("PlacesError", "An error occurred: " + status);
            }
        });
        toLobby.setOnClickListener(view -> {
            if(validate()) {
                to_lobby_page();
            }
            else {
                // TODO - Give error notification
            }
        });
    }


    /**
     * Helper method for creating json object with user supplied
     * information.
     *
     * Activity specific.
     */
    public JSONArray create_post_object() {
        JSONObject temp = new JSONObject();
        JSONArray arr = new JSONArray();
        try {
            // TODO - Figure out data orientation
            temp.put("driverDestLat", driverDestLat);
            temp.put("driverDestLng", driverDestLng);
            temp.put("driverLocLat", driverLocLat);
            temp.put("driverLocLng", driverLocLng);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arr.put(temp);
        return arr;
    }


    public boolean validate() {
        if (driverDestLat != 0 && driverDestLng != 0 && driverLocLat != 0 && driverLocLng != 0) {
            // TODO - Figure out link
//            JSONArray to_server = create_post_object();
//            String url = "http://coms-309-031.cs.iastate.edu:8080/" + "";
//            backend_io.serverPost(volleyQueue, url, to_server);
            return true;
        }
        else {
            return false;
        }
    }


    private void to_lobby_page(){
        Intent loadPage = new Intent(this, driver_lobby.class);

        Bundle extras = new Bundle();
        extras.putString("USERNAME", userName);
        loadPage.putExtras(extras);

        startActivity(loadPage);
    }
}