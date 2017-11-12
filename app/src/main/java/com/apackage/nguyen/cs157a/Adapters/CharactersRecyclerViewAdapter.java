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

    public static class CharactersViewHolder extends RecyclerView.ViewHolder {
        ImageView characterImage;
        TextView characterName;
        TextView characterClass;
        TextView characterWeapon;
        TextView characterArmor;
        TextView characterAccessory;

        CharactersViewHolder(View itemView) {
            super(itemView);

            characterName = itemView.findViewById(R.id.tvCharacterName);
            characterClass = itemView.findViewById(R.id.tvCharacterClass);
            characterWeapon = itemView.findViewById(R.id.tvCharacterWeapon);
            characterArmor = itemView.findViewById(R.id.tvCharacterArmor);
            characterAccessory = itemView.findViewById(R.id.tvCharacterAccessory);
            characterImage = itemView.findViewById(R.id.ivCharacterSprite);

        }
    }



}
