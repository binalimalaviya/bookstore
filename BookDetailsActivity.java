package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    private ImageView imageViewBookCover;
    private TextView textViewTitle, textViewAuthor, textViewPublisher, textViewPublicationDate, textViewDescription;
    private DatabaseHelper databaseHelper;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        databaseHelper = new DatabaseHelper(this);

        Button buttonBuy = findViewById(R.id.buttonbuy);
        Button buttoncancel=findViewById(R.id.buttoncancel);

        imageViewBookCover = findViewById(R.id.imageViewBookCover);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewPublisher = findViewById(R.id.textViewPublisher);
        textViewPublicationDate = findViewById(R.id.textViewPublicationDate);
        textViewDescription = findViewById(R.id.textViewDescription);

        // Retrieve the book details from the intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String publisher = intent.getStringExtra("publisher");
        String publicationDate = intent.getStringExtra("publicationDate");
        String description = intent.getStringExtra("description");
        int bookCover = intent.getIntExtra("bookCover", 0);
        email = getIntent().getStringExtra("email");

        // Set the book details in the views
        textViewTitle.setText(title);
        textViewAuthor.setText(author);
        textViewPublisher.setText(publisher);
        textViewPublicationDate.setText(publicationDate);
        textViewDescription.setText(description);
        imageViewBookCover.setImageResource(bookCover);

        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Debug logs to check values
                Log.d("BookDetailsActivity", "Email: " + email);
                Log.d("BookDetailsActivity", "Title: " + title);
                Log.d("BookDetailsActivity", "Author: " + author);
                Log.d("BookDetailsActivity", "Publisher: " + publisher);
                Log.d("BookDetailsActivity", "PublicationDate: " + publicationDate);
                Log.d("BookDetailsActivity", "Description: " + description);
                Log.d("BookDetailsActivity", "BookCover: " + bookCover);

                boolean isInserted = databaseHelper.insertPurchasedBook(email, title, author, publisher, publicationDate, description, bookCover);
                if (isInserted) {
                    Toast.makeText(BookDetailsActivity.this, "Book added to profile", Toast.LENGTH_SHORT).show();
                    Intent confirmationIntent = new Intent(BookDetailsActivity.this, ConfirmBuyActivity.class);
                    confirmationIntent.putExtra("email", email);
                    confirmationIntent.putExtra("title", title);
                    confirmationIntent.putExtra("bookCover", bookCover);
                    startActivity(confirmationIntent);
                } else {
                    Toast.makeText(BookDetailsActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailsActivity.this, BookActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

    }
}
