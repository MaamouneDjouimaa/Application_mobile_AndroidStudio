package com.example.authentification;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // Vérification des données de compte
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Veuillez entrer une adresse e-mail valide", Toast.LENGTH_SHORT).show();
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "Veuillez entrer un mot de passe valide (au moins 8 caractères avec 1 chiffre et 1 caractère spécial)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(String email) {
        // Vérification simple de l'e-mail (peut être améliorée selon vos besoins)
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Vérification du mot de passe (doit contenir au moins 8 caractères avec 1 chiffre et 1 caractère spécial)
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");
    }
}
