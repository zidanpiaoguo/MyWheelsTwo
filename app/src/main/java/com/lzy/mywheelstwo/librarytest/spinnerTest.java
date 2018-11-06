package com.lzy.mywheelstwo.librarytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.lzy.mylibrary.view.spinner.NiceSpinner;
import com.lzy.mywheelstwo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by bullet on 2018\8\2 0002.
 */

public class spinnerTest extends AppCompatActivity{

    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spinner);

        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);


        for (int i = 0; i < 50; i++) {
            list.add(i+"");
        }


//        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        niceSpinner.attachDataSource(list);

        Spinner spinner = findViewById(R.id.spinner);


    }
}
