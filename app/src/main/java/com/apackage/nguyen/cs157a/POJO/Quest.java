package com.apackage.nguyen.cs157a.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 11/15/17.
 */

public class Quest implements Parcelable {

    public static final Parcelable.Creator<Quest> CREATOR =
            new Parcelable.Creator<Quest>() {
                public Quest createFromParcel(Parcel in) {
                    return new Quest(in);
                }

                public Quest[] newArray(int size) {
                    return new Quest[size];
                }
            };
    private String questName;
    private String questGiver;
    private String questReward;
    private String questObjective;

    public Quest(String questName, String questGiver, String questReward, String questObjective) {
        this.questName = questName;
        this.questGiver = questGiver;
        this.questReward = questReward;
        this.questObjective = questObjective;
    }

    private Quest(Parcel in) {
        questName = in.readString();
        questGiver = in.readString();
        questReward = in.readString();
        questObjective = in.readString();
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestGiver() {
        return questGiver;
    }

    public void setQuestGiver(String questGiver) {
        this.questGiver = questGiver;
    }

    public String getQuestReward() {
        return questReward;
    }

    public void setQuestReward(String questReward) {
        this.questReward = questReward;
    }

    public String getQuestObjective() {
        return questObjective;
    }

    public void setQuestObjective(String questObjective) {
        this.questObjective = questObjective;
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questName);
        dest.writeString(questGiver);
        dest.writeString(questReward);
        dest.writeString(questObjective);
    }
}
