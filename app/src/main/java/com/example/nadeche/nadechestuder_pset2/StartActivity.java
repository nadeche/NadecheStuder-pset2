package com.example.nadeche.nadechestuder_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    //on click on 'Get started' button go to Story choice activity
    public void startStoryChoice(View startButton) {
        Intent next = new Intent(this, StoryChoice.class);
        startActivity(next);
    }
}
