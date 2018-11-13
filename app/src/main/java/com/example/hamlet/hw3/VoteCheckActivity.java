package com.example.hamlet.hw3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VoteCheckActivity extends Activity {

    // TODO: 11/13/18 READ ALL VOTES FROM THE FILE
    // TODO: 11/13/18 ADD CLEAR ALL BUTTON
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vote_check);

        //TextView voteTextView = findViewById(R.id.assign2_vote_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //voteTextView.setText(bundle.getString("text"));
    }
}
