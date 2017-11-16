package com.apackage.nguyen.cs157a.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = this.getClass().getSimpleName();

    private StringRequest stringRequest;

    private Intent welcomeIntent;

    private EditText usrAccount, usrPassword;
    private Button bRegister;
    private ProgressDialog progressDialog;

    private String account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usrAccount = findViewById(R.id.etUserAccount);
        usrPassword = findViewById(R.id.etUserPassword);
        bRegister = findViewById(R.id.bRegister);
        progressDialog = new ProgressDialog(RegisterActivity.this);

        bRegister.setOnClickListener(this);

    }

    private void register(){

        account = usrAccount.getText().toString();
        password = usrPassword.getText().toString();

        if (!validateForm(account, password)) {
            return;
        }
        progressDialog.setMessage("Please Wait!!!\nWhile we patch you up...");
        progressDialog.show();

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            String msg = jObj.getString("msg");

                            if(!error) {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                                welcomeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(welcomeIntent);
                                finish();
                            } else{
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usrAccount", account);
                params.put("usrPassword", password);
                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private boolean validateForm(String account, String password) {
        boolean valid = true;
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("Please enter all fields")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
            valid = false;
        }
        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bRegister)
            register();
    }

}
