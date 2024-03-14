package com.example.double_de_nombre_intent_;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DoubleActivity extends AppCompatActivity {

    private TextView outputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double);

        // Trouver l'élément de la vue par son ID
        outputNumber = findViewById(R.id.output_number);

        // Récupérer le nombre entré par l'utilisateur dans l'activité principale
        Intent intent = getIntent();
        int inputNumber = intent.getIntExtra("inputNumber", 0);

        // Calculer le double du nombre
        int outputNumberValue = inputNumber * 2;

        // Afficher le résultat dans le TextView de sortie
        outputNumber.setText(String.valueOf(outputNumberValue));

        // Envoyer le résultat à l'activité principale
        Intent returnIntent = new Intent();
        returnIntent.putExtra("outputNumber", outputNumberValue);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
