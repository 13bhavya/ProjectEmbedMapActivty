package com.example.mymapactivty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnnext;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnnext = findViewById(R.id.mapActivity);

        btnnext.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
    }
}
