package com.example.convertisseur;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private RadioGroup radioGroup;
    private RadioButton radioButtonDinarToEuro;
    private RadioButton radioButtonEuroToDinar;
    private Button button;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonDinarToEuro = findViewById(R.id.radioButton);
        radioButtonEuroToDinar = findViewById(R.id.radioButton2);
        button = findViewById(R.id.button);
        textViewResult = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
            }
        });
    }

    private void convertir() {
        float valeur = Float.parseFloat(editText.getText().toString());
        float resultat;
        if (radioButtonDinarToEuro.isChecked()) {
            resultat = dinarsToEuro(valeur);
            textViewResult.setText(resultat + " €");
        } else {
            resultat = euroToDinars(valeur);
            textViewResult.setText(resultat + " DZD");
        }
    }

    private float dinarsToEuro(float valeurDinar) {
        return (float) (valeurDinar * 0.0071);
    }

    private float euroToDinars(float valeurEuro) {
        return (float) (valeurEuro * 140.45);
    }
}
