package com.example.intero;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt1, edt2;
    private TextView txtLength, txtCommon;
    private Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        txtLength = findViewById(R.id.txtLength);
        txtCommon = findViewById(R.id.txtCommon);
        btnAction = findViewById(R.id.btnAction);

        registerForContextMenu(btnAction);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(btnAction);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_calculate:
                calculateLength();
                return true;
            case R.id.action_common:
                calculateCommon();
                return true;
            case R.id.action_clear:
                clearFields();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void calculateLength() {
        String word1 = edt1.getText().toString();
        String word2 = edt2.getText().toString();
        int length = word1.length() + word2.length();
        txtLength.setText(String.valueOf(length));
    }

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

    private void clearFields() {
        edt1.setText("");
        edt2.setText("");
        txtLength.setText("");
        txtCommon.setText("");
    }
}
