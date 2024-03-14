package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView contactListView;
    private String[] contacts = {"MAAMOUNE", "AMINE", "PACO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = findViewById(R.id.contactListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                }

                // Set the contact name in the inflated view
                String contactName = getItem(position);
                TextView textView = convertView.findViewById(android.R.id.text1);
                textView.setText(contactName);

                return convertView;
            }
        };

        contactListView.setAdapter(adapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedContact = contacts[position];
                Toast.makeText(MainActivity.this, "Clicked on " + selectedContact, Toast.LENGTH_SHORT).show();

                openContactDetailsActivity(selectedContact);
            }
        });
    }

    private void openContactDetailsActivity(String contactName) {
        Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
        intent.putExtra("contactName", contactName);

        startActivity(intent);
    }
}
