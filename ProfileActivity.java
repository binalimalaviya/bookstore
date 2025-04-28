package com.example.bookstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText etName, etEmail, etPassword, etMobile;
    private TextView textViewPurchasedBooksList;
    private Button btnSave, btnGoBack, btnpurchase;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find the EditText and Button views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etMobile = findViewById(R.id.etMobile);
        textViewPurchasedBooksList = findViewById(R.id.textViewPurchasedBooksList);
        btnSave = findViewById(R.id.btnSave);
        btnGoBack = findViewById(R.id.btnGoBack);
        btnpurchase = findViewById(R.id.btnpurchase);

        // Get the email from the Intent
        email = getIntent().getStringExtra("email");

        if (email == null) {
            Log.e("ProfileActivity", "Email is null");
            Toast.makeText(this, "Failed to load profile. Email is missing.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if email is null
            return;
        }

        // Load the user's profile
        loadUserProfile();

        // Set click listeners for the buttons
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Navigate back to the previous activity
            }
        });
        btnpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, buy_book.class);
                Toast.makeText(ProfileActivity.this, "Your book has been successfully purchased!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }

    private void loadUserProfile() {
        Cursor cursor = databaseHelper.getUserByEmail(email);
        if (cursor != null && cursor.moveToFirst()) {
            // Load user details into EditTexts
            etName.setText(cursor.getString(cursor.getColumnIndex("name")));
            etEmail.setText(cursor.getString(cursor.getColumnIndex("email")));
            etPassword.setText(cursor.getString(cursor.getColumnIndex("password")));
            etMobile.setText(cursor.getString(cursor.getColumnIndex("mobile_no")));

            // Load purchased books
            loadPurchasedBooks();
            cursor.close();
        }
    }

    private void saveUserProfile() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String mobileNo = etMobile.getText().toString().trim();

        // Check for empty fields
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || mobileNo.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update user profile in the database
        boolean isUpdated = databaseHelper.updateUser(email, name, password, mobileNo);
        if (isUpdated) {
            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadPurchasedBooks() {
        Cursor cursor = databaseHelper.getPurchasedBooksByEmail(email);
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder booksList = new StringBuilder();
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                booksList.append("Title: ").append(title).append("\n")
                        .append("Author: ").append(author).append("\n\n");
            }
            cursor.close();
            textViewPurchasedBooksList.setText(booksList.toString());
        } else {
            textViewPurchasedBooksList.setText("No books purchased.");
        }
    }
}
