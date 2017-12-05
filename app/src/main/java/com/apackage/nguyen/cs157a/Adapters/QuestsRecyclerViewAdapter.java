package com.apackage.nguyen.cs157a.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apackage.nguyen.cs157a.Activities.QuestInfo;
import com.apackage.nguyen.cs157a.POJO.Quest;
import com.apackage.nguyen.cs157a.R;

import java.util.List;

/**
 * Created by root on 11/15/17.
 */

public class QuestsRecyclerViewAdapter extends RecyclerView.Adapter<QuestsRecyclerViewAdapter.QuestsViewHolder> {

    Context context;
    List<Quest> questList;

    public QuestsRecyclerViewAdapter(Context context, List<Quest> questList) {
        this.context = context;
        this.questList = questList;
    }

    @Override
    public QuestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quest_list, parent, false);
        QuestsRecyclerViewAdapter.QuestsViewHolder ivh = new QuestsRecyclerViewAdapter.QuestsViewHolder(v);
        ivh.setOnClickListener(new QuestsViewHolder.ClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                switchActivity(v, pos);
            }
        });
        return ivh;
    }

    @Override
    public int getItemCount() {
        return questList.size();
    }

    @Override
    public void onBindViewHolder(QuestsViewHolder holder, int position) {
        Quest quest = questList.get(position);
        String questName = quest.getQuestName();
        holder.questName.setText(questName);
    }

    private void switchActivity(View v, int pos) {
        Quest mQuest = questList.get(pos);
        Context context = v.getContext();
        Intent questInfo = new Intent(context, QuestInfo.class);
        questInfo.putExtra("questObj", mQuest);
        context.startActivity(questInfo);
    }

    public static class QuestsViewHolder extends RecyclerView.ViewHolder {

        TextView questName;

        ClickListener mClickListener;

        QuestsViewHolder(View itemView) {
            super(itemView);

            questName = itemView.findViewById(R.id.tvQuestName);

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
