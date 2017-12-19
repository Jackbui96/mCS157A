package com.apackage.nguyen.cs157a.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apackage.nguyen.cs157a.Activities.CharacterInfo;
import com.apackage.nguyen.cs157a.Activities.EditCharacter;
import com.apackage.nguyen.cs157a.Activities.UserScreen;
import com.apackage.nguyen.cs157a.AddOn.MyVolley;
import com.apackage.nguyen.cs157a.Constant.Constant;
import com.apackage.nguyen.cs157a.POJO.Character;
import com.apackage.nguyen.cs157a.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jack on 10/28/17.
 */

public class CharactersRecyclerViewAdapter extends RecyclerView.Adapter<CharactersRecyclerViewAdapter.CharactersViewHolder> {

    static Character character;
    Context context;
    List<Character> characterList;

    public CharactersRecyclerViewAdapter(Context context, List<Character> characterList){
        this.context = context;
        this.characterList = characterList;
    }

    @Override
    public CharactersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_character_list, parent, false);
        CharactersRecyclerViewAdapter.CharactersViewHolder ivh = new CharactersRecyclerViewAdapter.CharactersViewHolder(v);

        ivh.setOnClickListener(new CharactersViewHolder.ClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                switchActivity(v, pos);
            }
        });

        return ivh;
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    @Override
    public void onBindViewHolder(CharactersViewHolder holder, int position) {

        character = characterList.get(position);

        final int characterOwner = character.getCharacterOwner();
        final String characterName = character.getCharacterName();
        final String characterClass = character.getCharacterClass();
        final String characterWeapon = character.getCharacterWeapon();
        final String characterArmor = character.getCharacterArmor();
        final String characterAccessory = character.getCharacterAccessory();

        holder.characterName.setText(characterName);
        holder.characterClass.setText(characterClass);
        holder.characterWeapon.setText(characterWeapon);
        holder.characterArmor.setText(characterArmor);
        holder.characterAccessory.setText(characterAccessory);
        holder.bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEdit(characterOwner, characterName);
            }
        });
        holder.bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_DELETECHARACTER,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jObj = new JSONObject(response.trim());
                                    boolean error = jObj.getBoolean("error");
                                    if (!error) {
                                        Intent intent = new Intent(context, UserScreen.class);
                                        intent.putExtra("uid", characterOwner);
                                        context.startActivity(intent);
                                    } else {
                                        Toast.makeText(context
                                                , "Fetch Fail"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    // JSON error
                                    e.printStackTrace();
                                    Toast.makeText(context
                                            , "Critical Error, contact help ASAP"
                                            , Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context
                                        , "Something went wrong, no response...." +
                                                "\nCheck Internet Connection"
                                        , Toast.LENGTH_LONG).show();
                            }

                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("uid", String.valueOf(characterOwner));
                        params.put("character_Name", characterName);
                        return params;
                    }
                };
                MyVolley.getInstance(context).addToRequestQueue(stringRequest);
            }
        });


        switch(characterClass){
            case "Mage":
                holder.characterImage.setImageResource(R.drawable.mage_sprite);
                break;
            case "Paladin":
                holder.characterImage.setImageResource(R.drawable.paladin_sprite);
                break;
            case "Miko":
                holder.characterImage.setImageResource(R.drawable.miko_sprite);
                break;
            case "Ranger":
                holder.characterImage.setImageResource(R.drawable.ranger_sprite);
                break;
            case "Bone Dragon":
                holder.characterImage.setImageResource(R.drawable.bone_dragon_sprite);
                break;
            case "Dogo":
                holder.characterImage.setImageResource(R.drawable.dogo_sprite);
                break;
            case "Warrior":
                holder.characterImage.setImageResource(R.drawable.warrior_sprite);
                break;
            case "Dragon Knight":
                holder.characterImage.setImageResource(R.drawable.dragon_knight_sprite);
                break;
            case "Hydralisk":
                holder.characterImage.setImageResource(R.drawable.hydralisk_sprite);
                break;
            case "Marine":
                holder.characterImage.setImageResource(R.drawable.marine_sprite);
                break;
            case "Mutalisk":
                holder.characterImage.setImageResource(R.drawable.mutalisk_sprite);
                break;
            case "Queen":
                holder.characterImage.setImageResource(R.drawable.queen_sprite);
                break;
            case "Roach":
                holder.characterImage.setImageResource(R.drawable.roach_sprite);
                break;
            case "Rogue":
                holder.characterImage.setImageResource(R.drawable.rogue_sprite);
                break;
            case "Skeleton":
                holder.characterImage.setImageResource(R.drawable.skeleton_sprite);
                break;
            case "Tamer":
                holder.characterImage.setImageResource(R.drawable.tamer_sprite);
                break;
            case "Witch":
                holder.characterImage.setImageResource(R.drawable.witch_sprite);
                break;
            case "Zergling":
                holder.characterImage.setImageResource(R.drawable.zergling_sprite);
                break;
        }

    }

    private void switchActivity(View v, int pos) {
        Character mCharacter = characterList.get(pos);
        Context context = v.getContext();
        Intent characterInfo = new Intent(context, CharacterInfo.class);
        characterInfo.putExtra("characterObj", mCharacter);
        context.startActivity(characterInfo);
    }

    private void switchEdit(int owner, String name) {
        Intent editIntent = new Intent(context, EditCharacter.class);
        editIntent.putExtra("owner", owner);
        editIntent.putExtra("characterName", name);
        context.startActivity(editIntent);
    }

    public static class CharactersViewHolder extends RecyclerView.ViewHolder {

        ImageView characterImage;
        TextView characterName;
        TextView characterClass;
        TextView characterWeapon;
        TextView characterArmor;
        TextView characterAccessory;
        Button bEdit;
        Button bDelete;

        ClickListener mClickListener;

        CharactersViewHolder(View itemView) {
            super(itemView);

            characterName = itemView.findViewById(R.id.tvCharacterName);
            characterClass = itemView.findViewById(R.id.tvCharacterClass);
            characterWeapon = itemView.findViewById(R.id.tvCharacterWeapon);
            characterArmor = itemView.findViewById(R.id.tvCharacterArmor);
            characterAccessory = itemView.findViewById(R.id.tvCharacterAccessory);
            characterImage = itemView.findViewById(R.id.ivCharacterSprite);
            bEdit = itemView.findViewById(R.id.bEdit);
            bDelete = itemView.findViewById(R.id.bDelete);

            // Set ClickListener for ViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, getAdapterPosition());
                }
            });

        }

        public void setOnClickListener(ClickListener clickListener) {
            mClickListener = clickListener;
        }

        // Interface to send callbacks
        public interface ClickListener {
            void onItemClick(View view, int position);
        }

    }

}
