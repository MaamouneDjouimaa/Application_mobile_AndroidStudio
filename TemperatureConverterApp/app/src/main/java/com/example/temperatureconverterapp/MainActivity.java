package com.example.temperatureconverterapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText inputEditText;
    private TextView resultTextView;
    private Button convertButton;
    private RadioGroup conversionRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);
        conversionRadioGroup = findViewById(R.id.conversionRadioGroup);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String inputText = inputEditText.getText().toString().trim();

        if (inputText.isEmpty()) {
            resultTextView.setText("Veuillez entrer une valeur");
            return;
        }

        double inputTemperature = Double.parseDouble(inputText);
        double convertedTemperature;

        int selectedId = conversionRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        if (selectedRadioButton.getText().equals("Celsius vers Fahrenheit")) {
            convertedTemperature = (inputTemperature * 9/5) + 32;
        } else {
            convertedTemperature = (inputTemperature - 32) * 5/9;
        }

        resultTextView.setText(String.valueOf(convertedTemperature));
    }
}
