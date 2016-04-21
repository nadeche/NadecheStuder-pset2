package com.example.nadeche.nadechestuder_pset2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/*
 * Created by Nadeche Studer on 20-4-2016.
 */
public class StoryChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_choice);

        //get all the file names in main/assets
        AssetManager assetManager = this.getAssets();
        String [] stories = new String[0];
        try {
            stories = assetManager.list("mad_libs");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //remove file extension for each list item
        for (int i =0; i < stories.length; i++) {
            stories[i] = stories[i].replaceFirst(".txt", "");
        }

        Collections.shuffle(Arrays.asList(stories));

        //list adapter
        ListAdapter storyList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stories);

        //get the list view id to fill
        ListView storyChoice = (ListView) findViewById(R.id.storyChoiceLiV);

        //fill the list view
        storyChoice.setAdapter(storyList);
    }
}
