package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmBuyActivity extends AppCompatActivity {

    private ImageView imageViewBookCover;
    private TextView textViewBookAuthor;
    private Button buttonGoToProfile, buttonGoToShopping;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comform_buy);

        imageViewBookCover = findViewById(R.id.imageViewBookCover2);
        textViewBookAuthor = findViewById(R.id.textViewBookAuthor2);
        buttonGoToProfile = findViewById(R.id.gotoprofile);
        buttonGoToShopping = findViewById(R.id.gotoshopping);

        // Retrieve the email from the intent
        userEmail = getIntent().getStringExtra("email");

        // Retrieve book details from the intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int bookCover = intent.getIntExtra("bookCover", 0);

        // Set book details in views
        imageViewBookCover.setImageResource(bookCover);
        textViewBookAuthor.setText("You have purchased: " + title);

        // Handle Go to Profile button click
        buttonGoToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(ConfirmBuyActivity.this, ProfileActivity.class);
                profileIntent.putExtra("email", userEmail);  // Pass the email to ProfileActivity
                startActivity(profileIntent);
            }
        });

        // Handle Go to Shopping button click
        buttonGoToShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoppingIntent = new Intent(ConfirmBuyActivity.this, BookActivity.class);
                shoppingIntent.putExtra("email", userEmail);
                startActivity(shoppingIntent);
            }
        });
    }
}
