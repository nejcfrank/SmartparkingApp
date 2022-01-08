package com.example.smartparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnrollmentActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView osebe;
    private String url = "https://smartparking-is.azurewebsites.net/api/v1/Enrollment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        osebe = (TextView) findViewById(R.id.enrollment);
    }

    public void prikaziEnrollment(View view){
        if (view != null){
            JsonArrayRequest request = new JsonArrayRequest(url,jsonArrayListener,errorListener)

            {
                @Override
                public Map<String,String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("ApiKey", "SecretKey");
                    return params;
                }
            };

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
                    String user = object.getString("userID");
                    String car = object.getString("carID");
                    String bus = object.getString("busID");
                    String spot = object.getString("spotID");
                    String arive = object.getString("arrival");
                    String depart = object.getString("departure");


                    data.add( "★" +" ("+ user + ") (" + car + ") (" + bus + ") (" + spot + ") (" + arive + ") (" + depart + ")");
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
    public void addEnrollmentActivity(View view) {
        Intent intent = new Intent(this,AddUserActivity.class); // mogoce tukaj narobe
        String message = " Add user to list.";
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }
}