package com.example.double_de_nombre_intent_;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
                int inputNumberValue = Integer.parseInt(inputText);

                // Appeler l'activité DoubleActivity pour calculer le double du nombre
                Intent intent = new Intent(MainActivity.this, DoubleActivity.class);
                intent.putExtra("inputNumber", inputNumberValue);
                startActivityForResult(intent, 1);
            }
        });
    }

    // Gérer le résultat renvoyé par l'activité DoubleActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int outputNumberValue = data.getIntExtra("outputNumber", 0);
            outputNumber.setText(String.valueOf(outputNumberValue));
        }
    }
}