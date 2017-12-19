package com.apackage.nguyen.cs157a.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jack on 11/03/17.
 */

public class Character implements Parcelable {

    public static final Parcelable.Creator<Character> CREATOR =
            new Parcelable.Creator<Character>() {
                public Character createFromParcel(Parcel in) {
                    return new Character(in);
                }

                public Character[] newArray(int size) {
                    return new Character[size];
                }
            };
    private int characterOwner;
    private String characterName;
    private String characterClass;
    private String characterWeapon;
    private String characterArmor;
    private String characterAccessory;

    public Character(int characterOwner, String characterName, String characterClass, String characterWeapon, String characterArmor, String characterAccessory) {
        this.characterOwner = characterOwner;
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.characterWeapon = characterWeapon;
        this.characterArmor = characterArmor;
        this.characterAccessory = characterAccessory;
    }

    private Character(Parcel in) {
        characterOwner = in.readInt();
        characterName = in.readString();
        characterClass = in.readString();
        characterWeapon = in.readString();
        characterArmor = in.readString();
        characterAccessory = in.readString();
    }

    public int getCharacterOwner() {
        return characterOwner;
    }

    public void setCharacterOwner(int characterOwner) {
        this.characterOwner = characterOwner;
    }

    public String getCharacterName(){
        return characterName;
    }

    public void setCharacterName(String characterName){
        this.characterName = characterName;
    }

    public String getCharacterClass(){
        return characterClass;
    }

    public void setCharacterClass(String characterClass){
        this.characterClass = characterClass;
    }

    public String getCharacterWeapon(){
        return characterWeapon;
    }

    public void setCharacterWeapon(String characterWeapon){
        this.characterWeapon = characterWeapon;
    }

    public String getCharacterArmor(){
        return characterArmor;
    }

    public void setCharacterArmor(String characterArmor){
        this.characterArmor = characterArmor;
    }

    public String getCharacterAccessory(){
        return characterAccessory;
    }

    public void setCharacterAccessory(String characterAccessory){
        this.characterAccessory = characterAccessory;
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(characterOwner);
        dest.writeString(characterName);
        dest.writeString(characterClass);
        dest.writeString(characterWeapon);
        dest.writeString(characterArmor);
        dest.writeString(characterAccessory);
    }

}
