package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1, editTextNumber2;
    private RadioGroup radioGroup;
    private RadioButton radioButtonAdd, radioButtonSub, radioButtonMult, radioButtonDiv;
    private TextView textViewResult;
    private String operationSelected;
    private boolean isKeyboardOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonAdd = findViewById(R.id.radioButtonAdd);
        radioButtonSub = findViewById(R.id.radioButtonSub);
        radioButtonMult = findViewById(R.id.radioButtonMul);
        radioButtonDiv = findViewById(R.id.radioButtonDiv);
        textViewResult = findViewById(R.id.textViewResult);

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        try {
            int num1 = Integer.parseInt(editTextNumber1.getText().toString());
            int num2 = Integer.parseInt(editTextNumber2.getText().toString());

            int result = 0;

            if (radioButtonAdd.isChecked()) {
                operationSelected = "+";
                result = num1 + num2;
            } else if (radioButtonSub.isChecked()) {
                operationSelected = "-";
                result = num1 - num2;
            } else if (radioButtonMult.isChecked()) {
                operationSelected = "*";
                result = num1 * num2;
            } else if (radioButtonDiv.isChecked()) {
                operationSelected = "/";
                if (num2 == 0) {
                    Toast.makeText(this, "La division par zéro est impossible", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    result = num1 / num2;
                }
            } else {
                Toast.makeText(this, "Sélectionnez une opération", Toast.LENGTH_SHORT).show();
                return;
            }

            textViewResult.setText(String.format("%d %s %d = %d", num1, operationSelected, num2, result));

            // Fermer le clavier
            if (isKeyboardOpen) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextNumber2.getWindowToken(), 0);
                isKeyboardOpen = false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir des nombres", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextNumber2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isKeyboardOpen = hasFocus;
            }
        });
    }


}
