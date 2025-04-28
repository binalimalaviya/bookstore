package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class after_login extends AppCompatActivity {
    private Button buttonprofile, buttonbookDetails;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_button);

        // Retrieve email passed from LoginActivity
        userEmail = getIntent().getStringExtra("email");

        if (userEmail == null) {
            Toast.makeText(this, "User email is missing", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if email is null
            return;
        }

        // Initialize your buttons
        buttonprofile = findViewById(R.id.btnprofile);
        buttonbookDetails = findViewById(R.id.btnbookdetails);

        buttonbookDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(after_login.this, BookActivity.class);
                intent.putExtra("email", userEmail);
                startActivity(intent);
            }
        });

        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(after_login.this, ProfileActivity.class);
                intent.putExtra("email", userEmail);
                startActivity(intent);
            }
        });
    }
}
