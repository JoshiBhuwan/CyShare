/*
 * @Author Tanay Parikh
 * @Author Josh Lawrinenko
 * @Author Bhuwan Joshi
 */

package com.example.cyshare_frontend;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.cyshare_frontend.model.backend_io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class login_page extends AppCompatActivity {
    /**
     * Hash Map Holds the all user data from the server,
     * speeds up future operations.
     * <p>
     * Needs to be updated frequently.
     */
    public HashMap<String, ArrayList<String>> usersInfo;
    /**
     * Volley thread for network activity
     */
    public RequestQueue volleyQueue;
    /**
     * Holds the attempts allowed and remaining
     */
    public int counter = 50;

    private TextView notificationMsg;
    private EditText userName;
    private EditText userPass;
    private Button button_to_splash;


    /**
     * Initialize is all the buttons and text Fields
     *
     * @param savedInstanceState android state data
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //region $init screen
        userName = findViewById(R.id.enterEmailReg);
        userPass = findViewById(R.id.enterPassReg);
        notificationMsg = findViewById(R.id.notificationWindow);
        button_to_splash = findViewById(R.id.login_button);
        Button button_to_register = findViewById(R.id.create_button);
        //endregion

        // get server data
        usersInfo = new HashMap<>();
        volleyQueue = Volley.newRequestQueue(this);
//        volleyQueue.start();
        String url = "http://coms-309-031.cs.iastate.edu:8080/users/all";
        backend_io.serverGetUserTables(volleyQueue, url, usersInfo);

        // give buttons functionality
        button_to_splash.setOnClickListener(view -> {
            if (!userName.getText().toString().equals("") && !userPass.getText().toString().equals("")) {
                validate(userName.getText().toString(), userPass.getText().toString());
            } else {
                String errorMsg = "Missing data, all fields must be filled out.";
                notificationMsg.setText(errorMsg);
            }
        });
        button_to_register.setOnClickListener(view ->
                to_registration()
        );
    }


    /**
     * Validates the the credentials from the user.
     * If the user does not exists then tells them to create a account.
     * If the user enters the wrong password from more than five time
     * then take away login button.
     * <p>
     * Activity specific.
     *
     * @param Email_from_login from user
     * @param pass_from_login  from user
     */
    public void validate(String Email_from_login, String pass_from_login) {
        // check if map contains email
        if (usersInfo.containsKey(Email_from_login)) {
            ArrayList<String> arr = usersInfo.get(Email_from_login);
            // check if password matches email
            if (arr.get(1) == null) runLogs();
            else if (pass_from_login.equals(arr.get(1))) {
                // check if driver or passenger
                if (usersInfo.get(Email_from_login).get(2) == null) runLogs();
                else if (usersInfo.get(Email_from_login).get(2).equals("PASSENGER")) to_passenger_splash();
                else to_driver_splash();
            }
            // else remove a remaining attempt
            else {
                counter--;
                String loginAttemptMsg = counter + " attempts remaining";
                notificationMsg.setText(loginAttemptMsg);
                if (counter == 0) {
//                    button_to_splash.setEnabled(false);
                }
            }
        }
        // else user does not exist in server tables
        else {
            String accountNotFoundMsg = "The user account doesn't exist. Please create a new account.";
            notificationMsg.setText(accountNotFoundMsg);
            // button_to_splash.setVisibility(View.GONE);
        }
    }

    private void runLogs() {
        ArrayList<String> temp1 = new ArrayList<>();
        temp1.addAll(usersInfo.keySet());
        Log.i("users size", String.valueOf(temp1.size()));

        for(int ind = 0; ind < temp1.size(); ind++) {
            Log.i("username " + String.valueOf(ind), temp1.get(ind));
            Log.i("id " + String.valueOf(ind), usersInfo.get(temp1.get(ind)).get(0));
            Log.i("password " + String.valueOf(ind), usersInfo.get(temp1.get(ind)).get(1));
            Log.i("role " + String.valueOf(ind), usersInfo.get(temp1.get(ind)).get(2));
        }
    }

    public void to_registration() {
        Intent loadRegister = new Intent(this, register_page.class);
        startActivity(loadRegister);
    }

    public void to_passenger_splash() {
        Intent loadSplash = new Intent(this, passenger_splash.class);

        Bundle extras = new Bundle();
        String userEmailStr = userName.getText().toString();
        extras.putString("USERNAME", userEmailStr);
        loadSplash.putExtras(extras);

        startActivity(loadSplash);
    }

    public void to_driver_splash() {
        Intent loadSplash = new Intent(this, driver_splash.class);

        Bundle extras = new Bundle();
        String userEmailStr = userName.getText().toString();
        extras.putString("USERNAME", userEmailStr);
        loadSplash.putExtras(extras);

        startActivity(loadSplash);
    }
}
