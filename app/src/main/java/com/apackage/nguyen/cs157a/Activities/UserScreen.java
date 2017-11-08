package com.apackage.nguyen.cs157a.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apackage.nguyen.cs157a.Adapters.CharactersRecyclerViewAdapter;
import com.apackage.nguyen.cs157a.AddOn.MyVolley;
import com.apackage.nguyen.cs157a.Constant.Constant;
import com.apackage.nguyen.cs157a.POJO.Character;
import com.apackage.nguyen.cs157a.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserScreen extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    private StringRequest stringRequest;

    private ProgressDialog progressDialog;
    private RecyclerView characterRecyclerView;

    private List<Character> characters = new ArrayList<>();

    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        uid = getIntent().getIntExtra("uid", 0);

        characterRecyclerView = findViewById(R.id.characterListView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        characterRecyclerView.setLayoutManager(llm);

        /*
        DatabaseHandler db = new DatabaseHandler(getContext());
        */

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETCHARACTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");

                            if (!error) {

                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    Log.d(TAG, jObj.getString(String.valueOf(i)));
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    Character character = new Character(
                                            sub.getString("Character_Name"),
                                            sub.getString("Character_Class"),
                                            sub.getString("Current_Weapon"),
                                            sub.getString("Current_Armor"),
                                            sub.getString("Current_Accessory"));
                                    characters.add(character);
                                }

                                //progressDialog.dismiss();

                                Toast.makeText(UserScreen.this, "Fetching Done", Toast.LENGTH_LONG).show();
                                CharactersRecyclerViewAdapter adapter = new CharactersRecyclerViewAdapter(getApplicationContext(), characters);
                                characterRecyclerView.setAdapter(adapter);

                            } else {
                                Toast.makeText(UserScreen.this, "Fetch Fail", Toast.LENGTH_LONG).show();
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
                        progressDialog.dismiss();
                        Toast.makeText(UserScreen.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("uid", String.valueOf(uid));
                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
