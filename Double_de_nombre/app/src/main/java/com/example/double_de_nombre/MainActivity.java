package com.example.double_de_nombre;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private TextView outputNumber;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Trouver les éléments de la vue par leur ID
        inputNumber = findViewById(R.id.input_number);
        outputNumber = findViewById(R.id.output_number);
        calculateButton = findViewById(R.id.calculate_button);

        // Ajouter un écouteur de clic au bouton de calcul
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le nombre entré par l'utilisateur
                String inputText = inputNumber.getText().toString();
                int inputNumber = Integer.parseInt(inputText);

                // Calculer le double du nombre
                int outputNumberValue = inputNumber * 2;

                // Afficher le résultat dans le TextView de sortie
                outputNumber.setText(String.valueOf(outputNumberValue));
            }
        });
    }
}