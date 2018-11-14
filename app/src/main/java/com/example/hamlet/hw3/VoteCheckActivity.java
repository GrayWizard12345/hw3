package com.example.hamlet.hw3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import corn.hw.R;

import java.io.IOException;
import java.util.ArrayList;

// TODO: 13.11.2018 does not work (read)
public class VoteCheckActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private TextView votesTextView;
    private ImageButton clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Vote", "count from VoteCheck " + VoteManager.count);

        initUI();
        initComponents();
    }

    private void initUI() {
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, 168);
        toolbar.setLayoutParams(params);
        //toolbar.setPopupTheme(R.style.AppTheme);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Vote List");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        votesTextView = new TextView(this);
        params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        votesTextView.setPadding(10, 10, 10, 10);
        votesTextView.setLayoutParams(params);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        layout.addView(toolbar, 0);
        layout.addView(votesTextView);

        setContentView(layout);
    }

    private void initComponents() {
        try {
            ArrayList<Vote> votes = VoteManager.readVote(this);
            Log.d("Vote", "votes length " + votes.size());
            votesTextView.setText(VoteManager.votesToString(votes));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred during reading!", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred during reading!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_vote_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.clear_vote)
            clear();

        return true;
    }

    public void clear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clear Votes");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", null);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        try {
            VoteManager.clearVotes(this);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred during deleting!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        VoteManager.count = 0;
        editor.putInt("count", VoteManager.count);
        editor.commit();
    }
}
