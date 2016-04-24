package com.example.nadeche.nadechestuder_pset2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/*
 * Created by Nadeche Studer
 *
 * This activity asks the user to give certain words to complete the mad lib story.
 */

public class FillStory extends AppCompatActivity {

    //declare story object to contain a mad lib
    Story madLib = null;

    //declare the used views
    TextView wordTypeTeV;
    TextView wordsLeftTeV;
    EditText wordFillEdT;

    //declare story title
    String selectedStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_story);

        //retrieve the selected story title from previous activity
        Intent previous = getIntent();
        selectedStory = previous.getStringExtra("story");

        //open the text file of the selected story and add file extension
        AssetManager assetManager = this.getAssets();
        InputStream text = null;
        try {
            text = assetManager.open("mad_libs/" + selectedStory + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create story object
        madLib = new Story(text);

        //get view id'd
        wordTypeTeV = (TextView) findViewById(R.id.wordTypeTeV);
        wordsLeftTeV = (TextView)findViewById(R.id.wordsLeftTeV);
        wordFillEdT = (EditText)findViewById(R.id.wordFillEdT);

        //setup the screen for the first word to fill in
        updateView();

    }

    //handle orientation change
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //hold on to current created story object
        outState.putSerializable("savedStory", madLib);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //retrieve story object and placeholders so far
        madLib = (Story) savedInstanceState.getSerializable("savedStory");

        //reload view's so the correct word is asked
        updateView();
    }

    public void onClickOkButton(View view) {

        //retrieve the given word from the EditText and save in the mad lib
        String word = wordFillEdT.getText().toString();
        madLib.fillInPlaceholder(word);

        //if there are placeholders left to fill
        if(!madLib.isFilledIn()){

            //clear previous word form editText
            wordFillEdT.setText("");

            //ask for new word
            updateView();
        }
        else{

            //create intent to go to last activity
            Intent finishStory = new Intent(getApplicationContext(),FinalStory.class );

            //bring the completed story to next activity and the story title
            finishStory.putExtra("finalStory", madLib);
            finishStory.putExtra("storyTitle", selectedStory);

            //launch activity to show the final story
            startActivity(finishStory);

            //kill this activity
            finish();
        }


    }

    private void updateView(){

        //retrieve the new placeholder
        String nextPlaceholder = madLib.getNextPlaceholder();

        //fill views with how many words to go and what kind of new word is asked
        wordTypeTeV.setText("Give me a " + nextPlaceholder);
        wordsLeftTeV.setText(String.valueOf(madLib.getPlaceholderRemainingCount()) + " to go!");
        wordFillEdT.setHint(nextPlaceholder);
    }
}
