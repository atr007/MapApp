package com.example.comp.mapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button mButtonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mButtonStart = (Button)findViewById(R.id.buttonStart);
    }

    public void onButtonStartClick(View view){
        Intent intent = new Intent(StartActivity.this, MapsActivity.class);
        startActivity(intent);
    }

}
