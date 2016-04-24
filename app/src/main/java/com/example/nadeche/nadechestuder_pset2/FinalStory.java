package com.example.nadeche.nadechestuder_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
 * Created by Nadeche Studer
 *
 * This activity shows the user their created mad lib story.
 * And an option to chose a new story to make an other one.
 */

public class FinalStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_story);

        //retrieve the created story object in last activity and the story title
        Intent previous = getIntent();
        Story finalStory = (Story) previous.getSerializableExtra("finalStory");
        String storyTitle = previous.getStringExtra("storyTitle");

        //get the textView id's to fill with text
        TextView title = (TextView)findViewById(R.id.titleTeV);
        TextView story = (TextView)findViewById(R.id.storyTeV);

        //fill the textViews with the users story and the title of the chosen story
        title.setText(storyTitle);
        story.setText(finalStory.toString());

    }

    public void onClickNewStory(View view) {

        //go back to the story list
        //(don't kill activity because when the user preses back by accident the story isn't immediately lost)
        Intent newStory = new Intent(getApplicationContext(), StoryChoice.class);
        startActivity(newStory);
    }
}
