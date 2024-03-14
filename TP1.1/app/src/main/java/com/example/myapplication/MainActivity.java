package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer les vues de l'interface utilisateur (EditText et Button)
        EditText editText = findViewById(R.id.editTextTextPersonName);
        Button sendButton = findViewById(R.id.button);

        // Ajouter un écouteur de clic au bouton d'envoi
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appeler la méthode displayMessage lorsque le bouton est cliqué
                displayMessage(v);
            }
        });
    }

    // Cette méthode est appelée lorsque le bouton d'envoi est cliqué
    public void displayMessage(View view) {
        // Récupérer le texte saisi dans le champ de texte EditText
        EditText editText = findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();

        // Afficher un message popup (Toast) avec le texte saisi
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
