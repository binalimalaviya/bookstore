package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class buy_book extends AppCompatActivity {

    private Button btnContinueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_book);

        btnContinueShopping = findViewById(R.id.btnContinueShopping);


        // Set click listener for the "Continue Shopping" button
        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the main bookstore activity or any other activity
                Intent intent = new Intent(buy_book.this, BookActivity.class);
                startActivity(intent);
                finish(); // Optional: close the current activity if not needed in backstack
            }
        });
    }
}
