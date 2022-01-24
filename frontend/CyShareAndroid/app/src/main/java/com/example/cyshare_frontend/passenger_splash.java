/*
 * @Author Tanay Parikh
 * @Author Josh Lawrinenko
 */

package com.example.cyshare_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class passenger_splash extends AppCompatActivity {
    /**
     * Volley thread for network activity
     */
    public RequestQueue volleyQueue;

    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        // Get bundle info
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userName = extras.getString("USERNAME");
    }
}
