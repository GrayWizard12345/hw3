package com.example.hamlet.hw3;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;

public class VoteManager {
    private ArrayList<Vote> votes;
    private String drinkArray[];
    private String foodArray[];

    public VoteManager(Resources res, int drinkArrayID, int foodArrayID){
        votes = new ArrayList<>();
        drinkArray = res.getStringArray(drinkArrayID);
        foodArray = res.getStringArray(foodArrayID);
    }

    public void addVote(String firstName, String lastName, boolean willCome, int drink, int food){
        Vote vote = new Vote(firstName, lastName, willCome, drink, food);
        Log.d(MainActivity.TAG, drinkArray[drink]);
        votes.add(vote);
    }

    public String votesToString(){
        String result = "";

        for (Vote v : votes){
            if(v.willCome()){
                result += String.format("-> %s %s will go to the party, and will drink %s and eat %s\n\n",
                        v.getFirstName(), v.getLastName(), drinkArray[v.getDrink()], foodArray[v.getFood()]);
            }else {
                result += String.format("-> %s %s will not come\n\n", v.getFirstName(), v.getLastName());
            }
        }

        return result;
    }
}
