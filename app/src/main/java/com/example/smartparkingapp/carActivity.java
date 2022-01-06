package com.example.smartparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class carActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView osebe;
    private String url = "https://smartparking-is1.azurewebsites.net/api/v1/Cars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        osebe = (TextView) findViewById(R.id.avtomobili);
    }

    public void prikaziAvtomobile(View view){
        if (view != null){
            JsonArrayRequest request = new JsonArrayRequest(url,jsonArrayListener,errorListener);
            requestQueue.add(request);
        }
    }

    private Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>(){
        @Override
        public void onResponse(JSONArray response){
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject object = response.getJSONObject(i);
                    String name = object.getString("carType");
                    String color = object.getString("carColor");


                    data.add( "★" +" "+ name + " " + color);
                } catch (JSONException e){
                    e.printStackTrace();
                    return;
                }
            }

            osebe.setText("");


            for (String row: data){
                String currentText = osebe.getText().toString();
                osebe.setText(currentText + "\n\n" + row);
            }

        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("REST error", error.getMessage());
        }
    };

    //POVEZAVA STRANI
    public static  final String EXTRA_MESSAGE ="com.example.smartparkingapp.MESSAGE";
    public void addCarActivity(View view) {
        Intent intent = new Intent(this, carAddActivity.class); // mogoce tukaj narobe
        String message = " Add car to list.";
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }
}