/*
 * @Author Tanay Parikh
 * @Author Josh Lawrinenko
 * @Author Bhuwan Joshi
 */

package com.example.cyshare_frontend.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class backend_io {

    /**
     * Method for requesting backend table information.
     * Requests json object from server then parses into map.
     *
     * @param vQueue     for creating volley thread
     * @param url        where to request data from
     * @param serverData map used for storing the json array(s)
     */
    public static void serverGetUserTables(RequestQueue vQueue, String url, HashMap<String, ArrayList<String>> serverData) {
        // create GET request
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            response -> {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject userObject = response.getJSONObject(i);
                        ArrayList<String> arr = new ArrayList<>();

                        arr.add(userObject.getString("id"));
                        arr.add(userObject.getString("password"));
                        arr.add(userObject.getString("role"));

//                        Log.i("id:", userObject.getString("id"));
//                        Log.i("pass:", userObject.getString("password"));
//                        Log.i("role:", userObject.getString("role"));

                        if (userObject.getString("role").equals("DRIVER")) {
                            JSONObject vehicle = userObject.getJSONObject("car");
                            arr.add(vehicle.getString("make"));
                            arr.add(vehicle.getString("model"));
                            arr.add(vehicle.getString("year"));
                            arr.add(vehicle.getString("color"));
                            arr.add(vehicle.getString("licensePlate"));
                        }

                        serverData.put(userObject.getString("userName"), arr);

//                        Log.i("user:", userObject.getString("userName"));
                    }

//                    Log.i("users size", String.valueOf(serverData.size()));

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            },
            error -> {/*TODO - Handle error*/});
        // add request to volley thread
        vQueue.add(jsonObjectRequest);
    }


    /**
     * Method for sending json objects to server.
     *
     * @param vQueue for creating volley thread
     * @param url    where to send data to
     * @param data   json array - must be pre-validated
     */
    public static void serverPost(RequestQueue vQueue, String url, JSONArray data) {
        // create POST request
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, url, data,
                response -> Log.d("JSON", String.valueOf(response)),
                error -> {/*TODO - Handle error*/});
        // add request to volley thread
        vQueue.add(jsonObjectRequest);

    }
}
