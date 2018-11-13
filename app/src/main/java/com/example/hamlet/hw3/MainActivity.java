package com.example.hamlet.hw3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener, RadioButton.OnCheckedChangeListener{

    private VoteManager voteManager;

    private LinearLayout drink_food_layout;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RadioButton agreeRBtn;
    private Button checkBtn;
    private Button voteBtn;
    private Spinner drinkSpinner;
    private Spinner foodSpinner;

    private void init() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(params);

        TextView tv = new TextView(this);
        tv.setText("Will you come to the party? If you come what type of food and drink you want?");
        tv.setTypeface(null, Typeface.BOLD);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        tv.setLayoutParams(params);
        root.addView(tv);

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

        root.addView(layout);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        radioGroup.setLayoutParams(params);
        agreeRBtn = new RadioButton(this);
        agreeRBtn.setText("Agree");
        agreeRBtn.setChecked(true);
        agreeRBtn.setId(0);
        agreeRBtn.setLayoutParams(params);
        RadioButton disagreeRBtn = new RadioButton(this);
        disagreeRBtn.setText("Disagree");
        disagreeRBtn.setLayoutParams(params);
        disagreeRBtn.setId(1);
        radioGroup.addView(agreeRBtn);
        radioGroup.addView(disagreeRBtn);
        root.addView(radioGroup);

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

        root.addView(drink_food_layout);

        voteBtn = new Button(this);
        voteBtn.setText("Vote");
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        voteBtn.setLayoutParams(params);
        root.addView(voteBtn);

        checkBtn = new Button(this);
        checkBtn.setText("Check votes");
        checkBtn.setLayoutParams(params);
        root.addView(checkBtn);

        setContentView(root);

        SpinnerAdapter adapter = new SpinnerAdapter(this, "liquids.txt");
        drinkSpinner.setAdapter(adapter);

        adapter = new SpinnerAdapter(this, "foods.txt");
        foodSpinner.setAdapter(adapter);

        voteBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);
        agreeRBtn.setOnCheckedChangeListener(this);
    }

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    // TODO: 11/13/18 WRITE VOTE OBJECT TO THE FILE
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
        int drink = drinkSpinner.getSelectedItemPosition();
        int food = foodSpinner.getSelectedItemPosition();
        voteManager.addVote(firstName, lastName, willCome, drink, food);
        Toast.makeText(this, "Vote is accepted successfully", Toast.LENGTH_SHORT).show();
        clearContent();
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
        Bundle bundle = new Bundle();
        bundle.putString("text", voteManager.votesToString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == voteBtn.getId()){
            addVote();
        }else if(view.getId() == checkBtn.getId()){
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
