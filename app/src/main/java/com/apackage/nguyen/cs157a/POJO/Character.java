package com.apackage.nguyen.cs157a.POJO;

/**
 * Created by Jack on 11/03/17.
 */

public class Character {

    private String characterName;
    private String characterClass;
    private String characterWeapon;
    private String characterArmor;
    private String characterAccessory;

    public Character(String characterName, String characterClass, String characterWeapon, String characterArmor, String characterAccessory){
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.characterWeapon = characterWeapon;
        this.characterArmor = characterArmor;
        this.characterAccessory = characterAccessory;
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

}
