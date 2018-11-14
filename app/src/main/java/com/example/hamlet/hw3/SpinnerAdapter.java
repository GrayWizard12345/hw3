package com.example.hamlet.hw3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpinnerAdapter extends ArrayAdapter<String> {

    public SpinnerAdapter(@NonNull Context context, int pathID) {
        super(context, android.R.layout.simple_spinner_dropdown_item);

        Scanner in = null;
        in = new Scanner(getContext().getResources().openRawResource(pathID));
        while (in.hasNext()){
            String item = in.next();
            add(item);
        }
        in.close();
    }
}

