package com.apackage.nguyen.cs157a.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apackage.nguyen.cs157a.POJO.Character;
import com.apackage.nguyen.cs157a.R;

import java.util.List;

/**
 * Created by Jack on 10/28/17.
 */

public class CharactersRecyclerViewAdapter extends RecyclerView.Adapter<CharactersRecyclerViewAdapter.CharactersViewHolder>{

    Context context;
    List<Character> characterList;

    public static class CharactersViewHolder extends RecyclerView.ViewHolder{
        ImageView characterImage;
        TextView characterName;
        TextView characterClass;
        TextView characterWeapon;
        TextView characterArmor;
        TextView characterAccessory;

        CharactersViewHolder(View itemView){
            super(itemView);

            characterName = (TextView) itemView.findViewById(R.id.tvCharacterName);
            characterClass = (TextView) itemView.findViewById(R.id.tvCharacterClass);
            characterWeapon = (TextView) itemView.findViewById(R.id.tvCharacterWeapon);
            characterArmor = (TextView) itemView.findViewById(R.id.tvCharacterArmor);
            characterAccessory = (TextView) itemView.findViewById(R.id.tvCharacterAccessory);
            characterImage = (ImageView) itemView.findViewById(R.id.ivCharacterSprite);

        }
    }

    public CharactersRecyclerViewAdapter(Context context, List<Character> characterList){
        this.context = context;
        this.characterList = characterList;
    }

    @Override
    public CharactersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_character_list, parent, false);
        CharactersRecyclerViewAdapter.CharactersViewHolder ivh = new CharactersRecyclerViewAdapter.CharactersViewHolder(v);

        /*
        ivh.setOnClickListener(new InformationViewHolder.ClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                switchActivity(v, pos);
            }

            @Override
            public void onItemLongClick(View v, int pos) {
                switchActivity(v, pos);
            }
        });
        */

        return ivh;
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    @Override
    public void onBindViewHolder(CharactersViewHolder holder, int position) {

        Character character = characterList.get(position);

        String characterName = character.getCharacterName();
        String characterClass = character.getCharacterClass();
        String characterWeapon = character.getCharacterWeapon();
        String characterArmor = character.getCharacterArmor();
        String characterAccessory = character.getCharacterAccessory();

        holder.characterName.setText(characterName);
        holder.characterClass.setText(characterClass);
        holder.characterWeapon.setText(characterWeapon);
        holder.characterArmor.setText(characterArmor);
        holder.characterAccessory.setText(characterAccessory);

        switch(characterClass){
            case "Medical":
                holder.characterImage.setImageResource(R.drawable.mage_sprite);
                break;
        }

    }



}
