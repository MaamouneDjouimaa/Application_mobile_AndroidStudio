package com.example.contact;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    private TextView nameTextView, numberTextView;
    private ImageView photoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        String contactName = getIntent().getStringExtra("contactName");

        nameTextView = findViewById(R.id.nameTextView);
        numberTextView = findViewById(R.id.numberTextView);
        photoImageView = findViewById(R.id.photoImageView);

        nameTextView.setText(contactName);
        numberTextView.setText("1234567890");
        photoImageView.setImageResource(R.drawable.photo);
    }
}
