package com.apackage.nguyen.cs157a.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apackage.nguyen.cs157a.AddOn.MyVolley;
import com.apackage.nguyen.cs157a.Constant.Constant;
import com.apackage.nguyen.cs157a.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CreateCharacter extends AppCompatActivity {

    private StringRequest stringRequest;

    private List<String> listOfClasses;
    private Spinner spinnerClasses;

    private EditText etCharacterName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        etCharacterName = (EditText) findViewById(R.id.etCharacterName);
        spinnerClasses = (Spinner) findViewById(R.id.spinnerClasses);

        getSpinnerList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listOfClasses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasses.setAdapter(adapter);

    }

    private void getSpinnerList(){

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETCLASSES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    listOfClasses.add(sub.getString("Class_Name"));
                                }

                            } else {
                                Toast.makeText(CreateCharacter.this, "Fetch Failed", Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
                        Toast.makeText(CreateCharacter.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                });

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        //return listOfClasses;
    }
}
