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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = this.getClass().getSimpleName();

    private StringRequest stringRequest;

    private Intent registerIntent;
    private Intent userIntent;

    private EditText usrAccount;
    private EditText usrPassword;
    private Button bLogin;
    private Button bRegister;
    private ProgressDialog progressDialog;

    private String account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usrAccount = findViewById(R.id.etUserAccount);
        usrPassword = findViewById(R.id.etUserPassword);
        bLogin = findViewById(R.id.bLogin);
        bRegister = findViewById(R.id.bRegister);
        progressDialog = new ProgressDialog(LoginActivity.this);

        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);

    }

    private void login(){

        account = usrAccount.getText().toString();
        password = usrPassword.getText().toString();

        if (!validateForm(account, password)) {
            return;
        }
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_SAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            String msg = jObj.getString("msg");
                            int uid = jObj.getInt("uid");

                            if(!error) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this
                                        , msg
                                        , Toast.LENGTH_SHORT).show();
                                userIntent = new Intent(LoginActivity.this, UserScreen.class);
                                userIntent.putExtra("uid", uid);
                                LoginActivity.this.startActivity(userIntent);
                            } else{
                                Toast.makeText(LoginActivity.this
                                        , msg
                                        , Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext()
                                    , "Critical Error, contact help ASAP"
                                    , Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this
                                , "Something went wrong, no response...." +
                                        "\nCheck Internet Connection"
                                , Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userAccount", account);
                params.put("userPassword", password);
                return params;
            }
        };

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    private void register(){
        registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(registerIntent);
    }

    private boolean validateForm(String account, String password) {
        boolean valid = true;
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Please Enter all Fields...")
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
        else if (i == R.id.bLogin)
            login();
    }

}