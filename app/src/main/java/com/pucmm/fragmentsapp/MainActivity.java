package com.pucmm.fragmentsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Replacing my temporary frame layout with my fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListFragment listFragment = new ListFragment();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            fragmentTransaction.replace(R.id.frame_list, listFragment);
        else
            fragmentTransaction.replace(R.id.frame_main, listFragment);

        fragmentTransaction.commit();
    }
}