package com.apackage.nguyen.cs157a.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apackage.nguyen.cs157a.Adapters.CharactersRecyclerViewAdapter;
import com.apackage.nguyen.cs157a.POJO.Character;
import com.apackage.nguyen.cs157a.R;

import java.util.ArrayList;
import java.util.List;

public class UserScreen extends AppCompatActivity {

    RecyclerView characterRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        characterRecyclerView = (RecyclerView) findViewById(R.id.characterListView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        characterRecyclerView.setLayoutManager(llm);

        /*
        DatabaseHandler db = new DatabaseHandler(getContext());
        */
        Character test = new Character(
                "Nao",
                "Mage",
                "Naga",
                "Banshee",
                "Follow-up Ring");
        List<Character> characters = new ArrayList<>();
        characters.add(test);

        CharactersRecyclerViewAdapter adapter = new CharactersRecyclerViewAdapter(this, characters);
        characterRecyclerView.setAdapter(adapter);

    }
}
