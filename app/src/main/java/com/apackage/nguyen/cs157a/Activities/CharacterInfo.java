package com.apackage.nguyen.cs157a.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apackage.nguyen.cs157a.Adapters.QuestsRecyclerViewAdapter;
import com.apackage.nguyen.cs157a.AddOn.MyVolley;
import com.apackage.nguyen.cs157a.Constant.Constant;
import com.apackage.nguyen.cs157a.POJO.Character;
import com.apackage.nguyen.cs157a.POJO.Quest;
import com.apackage.nguyen.cs157a.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CharacterInfo extends AppCompatActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    private StringRequest stringRequest;
    private List<Quest> quests = new ArrayList<>();

    private Character mCharacter;

    private RecyclerView questRecyclerView;
    private TextView tvCharacterName;
    private TextView tvCharacterClass;
    private TextView tvCharacterWeapon;
    private TextView tvCharacterArmor;
    private TextView tvCharacterAccessory;
    private ImageView ivCharacterSprite;
    private Button bAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);
        setTitle("Character Information");

        questRecyclerView = findViewById(R.id.questRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        questRecyclerView.setLayoutManager(llm);

        getAllInfo();
        getQuests();
        getUi();

    }

    private void getAllInfo() {
        mCharacter = getIntent().getParcelableExtra("characterObj");
    }

    private void getQuests() {
        stringRequest = new StringRequest(Request.Method.POST, Constant.URL_GETQUESTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jObj = new JSONObject(response.trim());
                            boolean error = jObj.getBoolean("error");
                            //progressDialog.setMessage("Please Wait!!!\nWhile we recall your heroes....");
                            //progressDialog.show();

                            if (!error) {

                                for (int i = 0; i < jObj.length() - 1; i++) {
                                    Log.d(TAG, jObj.getString(String.valueOf(i)));
                                    JSONObject sub = new JSONObject(jObj.getString(String.valueOf(i)));
                                    Quest quest = new Quest(
                                            sub.getString("Quest_Name"),
                                            sub.getString("Quest_Giver"),
                                            sub.getString("Quest_Reward"),
                                            sub.getString("Quest_Objective"));
                                    quests.add(quest);
                                }

                                //progressDialog.dismiss();

                                //Toast.makeText(UserScreen.this, "Recall Completed", Toast.LENGTH_LONG).show();
                                QuestsRecyclerViewAdapter adapter = new QuestsRecyclerViewAdapter(getApplicationContext(), quests);
                                questRecyclerView.setAdapter(adapter);

                            } else {
                                Toast.makeText(CharacterInfo.this, "Fetch Fail", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(CharacterInfo.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                });

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void getUi() {

        tvCharacterName = findViewById(R.id.tvCharacterName);
        tvCharacterName.setText(mCharacter.getCharacterName());
        tvCharacterClass = findViewById(R.id.tvCharacterClass);
        tvCharacterClass.setText(mCharacter.getCharacterClass());
        tvCharacterWeapon = findViewById(R.id.tvCharacterWeapon);
        tvCharacterWeapon.setText(mCharacter.getCharacterWeapon());
        tvCharacterArmor = findViewById(R.id.tvCharacterArmor);
        tvCharacterArmor.setText(mCharacter.getCharacterArmor());
        tvCharacterAccessory = findViewById(R.id.tvCharacterAccessory);
        tvCharacterAccessory.setText(mCharacter.getCharacterAccessory());
        ivCharacterSprite = findViewById(R.id.ivCharacterSprite);
        switch (mCharacter.getCharacterClass()) {
            case "Mage":
                ivCharacterSprite.setImageResource(R.drawable.mage_sprite);
                break;
            case "Paladin":
                ivCharacterSprite.setImageResource(R.drawable.paladin_sprite);
                break;
            case "Miko":
                ivCharacterSprite.setImageResource(R.drawable.miko_sprite);
                break;
            case "Ranger":
                ivCharacterSprite.setImageResource(R.drawable.ranger_sprite);
                break;
            case "Bone Dragon":
                ivCharacterSprite.setImageResource(R.drawable.bone_dragon_sprite);
                break;
            case "Dogo":
                ivCharacterSprite.setImageResource(R.drawable.dogo_sprite);
                break;
            case "Warrior":
                ivCharacterSprite.setImageResource(R.drawable.warrior_sprite);
                break;
            case "Dragon Knight":
                ivCharacterSprite.setImageResource(R.drawable.dragon_knight_sprite);
                break;
            case "Hydralisk":
                ivCharacterSprite.setImageResource(R.drawable.hydralisk_sprite);
                break;
            case "Marine":
                ivCharacterSprite.setImageResource(R.drawable.marine_sprite);
                break;
            case "Mutalisk":
                ivCharacterSprite.setImageResource(R.drawable.mutalisk_sprite);
                break;
            case "Queen":
                ivCharacterSprite.setImageResource(R.drawable.queen_sprite);
                break;
            case "Roach":
                ivCharacterSprite.setImageResource(R.drawable.roach_sprite);
                break;
            case "Rogue":
                ivCharacterSprite.setImageResource(R.drawable.rogue_sprite);
                break;
            case "Skeleton":
                ivCharacterSprite.setImageResource(R.drawable.skeleton_sprite);
                break;
            case "Tamer":
                ivCharacterSprite.setImageResource(R.drawable.tamer_sprite);
                break;
            case "Witch":
                ivCharacterSprite.setImageResource(R.drawable.witch_sprite);
                break;
            case "Zergling":
                ivCharacterSprite.setImageResource(R.drawable.zergling_sprite);
                break;
        }
        //bAccept = (Button) findViewById(R.id.bAccept);
        //bAccept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        //if (i == R.id.bAccept){

        //}
    }

}
