package com.example.moyenne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Vérifie si tous les champs sont remplis
                if (TextUtils.isEmpty(editText1.getText().toString())
                        || TextUtils.isEmpty(editText2.getText().toString())
                        || TextUtils.isEmpty(editText3.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertit les champs en nombres
                int note1 = Integer.parseInt(editText1.getText().toString());
                int note2 = Integer.parseInt(editText2.getText().toString());
                int note3 = Integer.parseInt(editText3.getText().toString());

                // Calcule la moyenne
                double moyenne = (note1 + note2 + note3) / 3.0;

                // Affiche l'interface de réussite ou recale selon la moyenne
                if (moyenne >= 10) {
                    Intent intent = new Intent(MainActivity.this, Reussite.class);
                    intent.putExtra("moyenne", moyenne);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, Recale.class);
                    intent.putExtra("moyenne", moyenne);
                    startActivity(intent);
                }
            }
        });
    }
}
