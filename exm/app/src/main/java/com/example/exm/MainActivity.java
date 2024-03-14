package com.example.exm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt1, edt2;
    private TextView txtLength, txtCommon;
    private Button btnLength, btnCommon, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        txtLength = findViewById(R.id.txtLength);
        txtCommon = findViewById(R.id.txtCommon);
        btnLength = findViewById(R.id.btnLength);
        btnCommon = findViewById(R.id.btnCommon);
        btnClear = findViewById(R.id.btnClear);

        // Set click listeners
        btnLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLength();
            }
        });

        btnCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCommon();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    // Calculate the sum of the lengths of the two words
    private void calculateLength() {
        String word1 = edt1.getText().toString();
        String word2 = edt2.getText().toString();
        int length = word1.length() + word2.length();
        txtLength.setText(String.valueOf(length));
    }

    // Calculate the number of common characters between the two words
    private void calculateCommon() {
        String word1 = edt1.getText().toString();
        String word2 = edt2.getText().toString();
        int commonCount = 0;

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    commonCount++;
                }
            }
        }

        txtCommon.setText(String.valueOf(commonCount));
    }

    // Clear all fields
    private void clearFields() {
        edt1.setText("");
        edt2.setText("");
        txtLength.setText("");
        txtCommon.setText("");
    }
}
