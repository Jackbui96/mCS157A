package com.apackage.nguyen.cs157a.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apackage.nguyen.cs157a.AddOn.MyVolley;
import com.apackage.nguyen.cs157a.Constant.Constant;
import com.apackage.nguyen.cs157a.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditCharacter extends AppCompatActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();

    private StringRequest stringRequest;

    private List<String> listOfClasses;
    private List<String> listOfWeapons;
    private List<String> listOfArmors;
    private List<String> listOfAccessories;

    private Spinner spinnerClasses;
    private Spinner spinnerWeapons;
    private Spinner spinnerArmors;
    private Spinner spinnerAccessories;

    private String selectClass;
    private String selectWeapon;
    private String selectArmor;
    private String selectAccessory;
    private int uid;
    private String characterName;

    private TextView tvCharacterName;
    private Button bEdit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);

        uid = getIntent().getIntExtra("owner", 0);
        characterName = getIntent().getStringExtra("characterName");

        progressDialog = new ProgressDialog(EditCharacter.this);

        tvCharacterName = findViewById(R.id.tvCharacterName);
        tvCharacterName.setText(characterName);
        spinnerClasses = findViewById(R.id.spinnerClasses);
        spinnerWeapons = findViewById(R.id.spinnerWeapons);
        spinnerArmors = findViewById(R.id.spinnerArmors);
        spinnerAccessories = findViewById(R.id.spinnerAccessories);
        bEdit = findViewById(R.id.bEdit);
        bEdit.setOnClickListener(this);

        getSpinnerList(new CallBack() {
            @Override
            public void onSuccess(List<String> listOfClasses) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, listOfClasses);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerClasses.setAdapter(adapter);
                spinnerClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d(TAG, spinnerClasses.getItemAtPosition(i).toString());

                        selectClass = spinnerClasses.getItemAtPosition(i).toString();

                        getWeaponList(new CallBack() {
                            @Override
                            public void onSuccess(List<String> listOfWeapons) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                        android.R.layout.simple_spinner_dropdown_item, listOfWeapons);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerWeapons.setAdapter(adapter);
                                spinnerWeapons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        selectWeapon = spinnerWeapons.getItemAtPosition(i).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }

                            @Override
                            public void onFail(String msg) {
                                // Do Stuff
                            }
                        }, selectClass);


                        getArmorList(new CallBack() {
                            @Override
                            public void onSuccess(List<String> listOfArmors) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                        android.R.layout.simple_spinner_dropdown_item, listOfArmors);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerArmors.setAdapter(adapter);
                                spinnerArmors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        selectArmor = spinnerArmors.getItemAtPosition(i).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }

                            @Override
                            public void onFail(String msg) {
                                // Do Stuff
                            }
                        }, selectClass);

                        getAccessoryList(new CallBack() {
                            @Override
                            public void onSuccess(List<String> listOfAccessories) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                        android.R.layout.simple_spinner_dropdown_item, listOfAccessories);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerAccessories.setAdapter(adapter);
                                spinnerAccessories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        selectAccessory = spinnerAccessories.getItemAtPosition(i).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }

                            @Override
                            public void onFail(String msg) {
                                // Do Stuff
                            }
                        }, selectClass);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
            }
        });

    }

    private void getSpinnerList(final CallBack onCallBack) {

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETCLASSES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            listOfClasses = new ArrayList<>();
                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    listOfClasses.add(sub.getString("Class_Name"));
                                }

                            } else {
                                Toast.makeText(EditCharacter.this
                                        , "Fetch Failed"
                                        , Toast.LENGTH_LONG).show();
                            }
                            onCallBack.onSuccess(listOfClasses);


                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext()
                                    , "Critical Error, contact help ASAP"
                                    , Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditCharacter.this
                                , "Something went wrong, no response...." +
                                        "\nCheck Internet Connection"
                                , Toast.LENGTH_LONG).show();
                    }
                });

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void getWeaponList(final CallBack onCallBack, String selectClass) {

        final String selectedClass = selectClass;

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETEQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            listOfWeapons = new ArrayList<>();
                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    Log.d(TAG, jObj.getString(String.valueOf(i)));
                                    listOfWeapons.add(sub.getString("Name"));
                                }
                            } else {
                                Toast.makeText(EditCharacter.this
                                        , "Fetch Failed"
                                        , Toast.LENGTH_LONG).show();
                            }
                            onCallBack.onSuccess(listOfWeapons);

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext()
                                    , "Critical Error, contact help ASAP"
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditCharacter.this
                                , "Something went wrong, no response...." +
                                        "\nCheck Internet Connection"
                                , Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "Weapons");
                params.put("class", selectedClass);
                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void getArmorList(final CallBack onCallBack, String selectClass) {

        final String selectedClass = selectClass;

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETEQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            listOfArmors = new ArrayList<>();
                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    listOfArmors.add(sub.getString("Name"));
                                }

                            } else {
                                Toast.makeText(EditCharacter.this
                                        , "Fetch Failed"
                                        , Toast.LENGTH_LONG).show();
                            }
                            onCallBack.onSuccess(listOfArmors);


                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext()
                                    , "Critical Error, contact help ASAP"
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditCharacter.this
                                , "Something went wrong, no response...." +
                                        "\nCheck Internet Connection"
                                , Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "Armors");
                params.put("class", selectedClass);
                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void getAccessoryList(final CallBack onCallBack, String selectClass) {

        final String selectedClass = selectClass;

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETEQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            listOfAccessories = new ArrayList<>();
                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    listOfAccessories.add(sub.getString("Name"));
                                }

                            } else {
                                Toast.makeText(EditCharacter.this
                                        , "Fetch Failed"
                                        , Toast.LENGTH_LONG).show();
                            }
                            onCallBack.onSuccess(listOfAccessories);


                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext()
                                    , "Critical Error, contact help ASAP"
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditCharacter.this
                                , "Something went wrong, no response...." +
                                        "\nCheck Internet Connection"
                                , Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "Accessories");
                params.put("class", selectedClass);
                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void edit() {

        progressDialog.setMessage("Please Wait!!!\nModifying...");
        progressDialog.show();

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_EDITCHARACTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            String msg = jObj.getString("msg");

                            if (!error) {
                                progressDialog.dismiss();
                                Toast.makeText(EditCharacter.this
                                        , msg
                                        , Toast.LENGTH_SHORT).show();
                                Intent userScreen = new Intent(
                                        EditCharacter.this,
                                        UserScreen.class);
                                userScreen.putExtra("uid", uid);
                                EditCharacter.this.startActivity(userScreen);
                                finish();
                            } else {
                                Toast.makeText(EditCharacter.this
                                        , msg
                                        , Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext()
                                    , "Critical Error, contact help ASAP"
                                    , Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditCharacter.this
                                , "Something went wrong, no response...." +
                                        "\nCheck Internet Connection"
                                , Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("uid", String.valueOf(uid));
                params.put("name", characterName);
                params.put("class", selectClass);
                params.put("weapon", selectWeapon);
                params.put("armor", selectArmor);
                params.put("accessory", selectAccessory);

                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bEdit)
            edit();
    }

    private interface CallBack {
        void onSuccess(List<String> listOfClasses);

        void onFail(String msg);
    }
}
