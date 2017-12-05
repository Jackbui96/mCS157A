package com.apackage.nguyen.cs157a.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.apackage.nguyen.cs157a.POJO.Quest;
import com.apackage.nguyen.cs157a.R;

public class QuestInfo extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    private Quest mQuest;

    private TextView tvQuestName;
    private TextView tvQuestGiver;
    private TextView tvQuestReward;
    private TextView tvQuestObjective;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_info);

        getAllInfo();
        getUi();

    }

    private void getAllInfo() {
        mQuest = getIntent().getParcelableExtra("questObj");
    }

    private void getUi() {

        tvQuestName = findViewById(R.id.tvQuestName);
        tvQuestName.setText(mQuest.getQuestName());
        tvQuestGiver = findViewById(R.id.tvQuestGiver);
        tvQuestGiver.setText("Giver: " + mQuest.getQuestGiver());
        tvQuestReward = findViewById(R.id.tvQuestReward);
        tvQuestReward.setText("Reward: " + mQuest.getQuestReward());
        tvQuestObjective = findViewById(R.id.tvQuestObjective);
        tvQuestObjective.setText("Objective: " + mQuest.getQuestObjective());

    }

}
