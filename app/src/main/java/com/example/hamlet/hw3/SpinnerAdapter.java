package com.example.hamlet.hw3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Scanner;

public class SpinnerAdapter extends ArrayAdapter<String> {

    public SpinnerAdapter(@NonNull Context context, String filePath) {
        super(context, android.R.layout.simple_spinner_dropdown_item);

        Scanner in = new Scanner(filePath);
        while (in.hasNext()){
            String item = in.next();
            add(item);
            Log.d("Adapter", item);
        }
        in.close();
    }
}

