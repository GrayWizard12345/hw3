package com.example.hamlet.hw3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.SpinnerAdapter;
import corn.hw.R;

import java.io.IOException;
import java.util.Timer;

public class MainActivity extends Activity implements View.OnClickListener, RadioButton.OnCheckedChangeListener{

    private LinearLayout drink_food_layout;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RadioButton agreeRBtn;
    private Button checkBtn;
    private Button voteBtn;
    private Spinner drinkSpinner;
    private Spinner foodSpinner;

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
        initComponents();

        delete();
    }

    private void delete() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count", 0);
        editor.commit();

        VoteManager.count = 0;
    }

    private void initUI() {
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        rootLayout.setLayoutParams(params);

        Toolbar toolbar = new Toolbar(this);
        params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, 168);
        toolbar.setLayoutParams(params);
        toolbar.setTitle("Vote");
        toolbar.setPopupTheme(R.style.AppTheme);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitleTextColor(Color.WHITE);
        rootLayout.addView(toolbar, 0);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mainLayout.setPadding(20, 20, 20, 20);
        mainLayout.setLayoutParams(params);
        rootLayout.addView(mainLayout);

        TextView tv = new TextView(this);
        tv.setText("Will you come to the party? If you come what type of food and drink you want?");
        tv.setTypeface(null, Typeface.BOLD);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        tv.setLayoutParams(params);
        mainLayout.addView(tv);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        layout.setLayoutParams(params);

        firstNameEditText = new EditText(this);
        firstNameEditText.setHint("First Name");
        params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT,2);
        params.setMargins(10, 10, 10, 10);
        firstNameEditText.setLayoutParams(params);
        layout.addView(firstNameEditText);

        lastNameEditText = new EditText(this);
        lastNameEditText.setHint("Last Name");
        lastNameEditText.setLayoutParams(params);
        layout.addView(lastNameEditText);

        mainLayout.addView(layout);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        params.gravity = Gravity.CENTER;
        radioGroup.setLayoutParams(params);

        agreeRBtn = new RadioButton(this);
        agreeRBtn.setText("Agree");
        agreeRBtn.setChecked(true);
        agreeRBtn.setId(0);
        RadioButton disagreeRBtn = new RadioButton(this);
        disagreeRBtn.setText("Disagree");
        disagreeRBtn.setId(1);

        radioGroup.addView(agreeRBtn, params);
        radioGroup.addView(disagreeRBtn, params);
        mainLayout.addView(radioGroup);

        drink_food_layout = new LinearLayout(this);
        drink_food_layout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        drink_food_layout.setLayoutParams(params);

        tv = new TextView(this);
        tv.setText("Drinks");
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        tv.setLayoutParams(params);
        drink_food_layout.addView(tv);

        drinkSpinner = new Spinner(this);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        drinkSpinner.setLayoutParams(params);
        drink_food_layout.addView(drinkSpinner);

        tv = new TextView(this);
        tv.setText("Food");
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        tv.setLayoutParams(params);
        drink_food_layout.addView(tv);

        foodSpinner = new Spinner(this);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        foodSpinner.setLayoutParams(params);
        drink_food_layout.addView(foodSpinner);

        mainLayout.addView(drink_food_layout);

        voteBtn = new Button(this);
        voteBtn.setText("Vote");
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        voteBtn.setLayoutParams(params);
        mainLayout.addView(voteBtn);

        checkBtn = new Button(this);
        checkBtn.setText("Check votes");
        checkBtn.setLayoutParams(params);
        mainLayout.addView(checkBtn);

        setContentView(rootLayout);
    }

    private void initComponents() {
        // TODO: 13.11.2018 CHANGE THE SPINNER DROP DOWN STYLE
        android.widget.SpinnerAdapter adapter = new com.example.hamlet.hw3.SpinnerAdapter(this, R.raw.liquids);
        drinkSpinner.setAdapter(adapter);

        adapter = new com.example.hamlet.hw3.SpinnerAdapter(this, R.raw.foods);
        foodSpinner.setAdapter(adapter);

        voteBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);
        agreeRBtn.setOnCheckedChangeListener(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        VoteManager.count = sharedPreferences.getInt("count", 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count", VoteManager.count);
        editor.commit();
    }

    private void addVote() {
        String firstName = firstNameEditText.getText().toString();
        if(firstName.trim().isEmpty()){
            Toast.makeText(this, "Fill all gaps", Toast.LENGTH_SHORT).show();
            return;
        }

        String lastName = lastNameEditText.getText().toString();
        if(lastName.trim().isEmpty()){
            Toast.makeText(this, "Fill all gaps", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean willCome = agreeRBtn.isChecked();
        String drink = drinkSpinner.getSelectedItem().toString();
        String food = foodSpinner.getSelectedItem().toString();
        Vote vote = new Vote(firstName, lastName, willCome, drink, food);
        try {
            VoteManager.writeVote(this, vote);
            Toast.makeText(this, "Vote is accepted successfully", Toast.LENGTH_SHORT).show();
            clearContent();
        } catch (IOException e) {
            Toast.makeText(this, "Vote is not accepted!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void clearContent() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        agreeRBtn.setChecked(true);
        drinkSpinner.setSelection(0);
        foodSpinner.setSelection(0);
    }

    private void showVotes() {
        Intent intent = new Intent(this, VoteCheckActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        if(btn == voteBtn){
            addVote();
        }else if(view == checkBtn){
            showVotes();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(!isChecked)
            drink_food_layout.setVisibility(View.GONE);
        else
            drink_food_layout.setVisibility(View.VISIBLE);
    }
}
