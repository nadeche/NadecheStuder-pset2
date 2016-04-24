package com.example.nadeche.nadechestuder_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
* Created by Nadeche Studer
*
* This activity is the entrance of the app.
* It explains the mad lib concept to the user and allows them to move on.
*/

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    //on click of 'Get started' button go to story choice activity
    public void startStoryChoice(View startButton) {

        //go to story choice activity
        Intent next = new Intent(this, StoryChoice.class);
        startActivity(next);

        //kill this activity
        finish();
    }
}
