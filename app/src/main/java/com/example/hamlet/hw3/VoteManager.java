package com.example.hamlet.hw3;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class VoteManager {

    private static final String filePath = "votes.txt";
    public static int count = 0;

    private VoteManager(){}

    public static String votesToString(ArrayList<Vote> votes){
        String result = "";

        for (Vote v : votes) {
            result += v.toString();
        }

        return result;
    }

    public static void writeVote(Context context, Vote vote) throws IOException {
        FileOutputStream fos = context.openFileOutput(filePath, Context.MODE_APPEND);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(vote);
        os.close();
        fos.close();
        count++;

        Log.d("Vote", "count: " + count);
    }

    public static ArrayList<Vote> readVote(Context context) throws IOException, ClassNotFoundException {
        ArrayList<Vote> votes = new ArrayList<>();
        FileInputStream fis = context.openFileInput(filePath);
        ObjectInputStream is = new ObjectInputStream(fis);
        for (int i = 0; i < count; i++){
            Vote v = (Vote) is.readObject();
            Log.d("Vote", v.toString());
            votes.add(v);
        }
        is.close();
        fis.close();

        return votes;
    }

    public static void clearVotes(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(filePath, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeChars("");
        os.close();
        fos.close();
        count++;
    }
}
