package com.tamago.viewloader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tamago.loader.ViewLoader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ViewLoader.with(MainActivity.this).on(R.id.text).fadein(true).roundCorners(true).setCorners(40).start();
    }
}
