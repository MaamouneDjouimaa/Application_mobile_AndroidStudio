package com.example.moyenne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Reussite extends AppCompatActivity {
    private TextView textViewMoyenne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reussite);

        textViewMoyenne = findViewById(R.id.textViewMoyenne);

        Intent intent = getIntent();
        double moyenne = intent.getDoubleExtra("moyenne", 0.0);

        textViewMoyenne.setText("Moyenne : " + moyenne);
    }
}