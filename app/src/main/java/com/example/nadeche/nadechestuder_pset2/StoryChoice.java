package com.example.nadeche.nadechestuder_pset2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        //randomize the order of the list
        Collections.shuffle(Arrays.asList(stories));

        //list adapter
        ListAdapter storyList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stories);

        //get the list view id to fill
        ListView storyChoice = (ListView) findViewById(R.id.storyChoiceLiV);

        //fill the list view
        storyChoice.setAdapter(storyList);

        //listen for list item clicks
        storyChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the selected story to a string
                //String chosenStory = (String) parent.getItemAtPosition(position);
                String chosenStory = ((TextView)view).getText().toString();

                //display a short toast
                Toast.makeText(StoryChoice.this,chosenStory, Toast.LENGTH_SHORT).show();

                //create intent to go to next activity
                Intent next = new Intent(getApplicationContext(),FillStory.class );

                //bring chosen story to next activity
                next.putExtra("story", chosenStory);
                //launch next activity
                startActivity(next);
            }
        });
    }
}
