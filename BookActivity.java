package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {
    private String email;
    ListView listView;
    SearchView search_bar;

    ArrayAdapter<String> adapter;
    ArrayList<String> mylist;

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    private Handler sliderHandler;
    private Runnable sliderRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookstore);

        DatabaseHelper db = new DatabaseHelper(this);
        // Get references to the buttons
        Button buttonBack = findViewById(R.id.btnGoBack);
        Button buttonSeeMore1 = findViewById(R.id.buttonSeeMore1);
        Button buttonSeeMore2 = findViewById(R.id.buttonSeeMore2);
        Button buttonSeeMore3 = findViewById(R.id.buttonSeeMore3);
        Button buttonSeeMore4 = findViewById(R.id.buttonSeeMore4);
        Button buttonSeeMore5 = findViewById(R.id.buttonSeeMore5);
        Button buttonSeeMore6 = findViewById(R.id.buttonSeeMore6);
        Button buttonSeeMore7 = findViewById(R.id.buttonSeeMore7);
        Button buttonSeeMore8 = findViewById(R.id.buttonSeeMore8);
        Button buttonSeeMore9 = findViewById(R.id.buttonSeeMore9);
        Button buttonSeeMore10 = findViewById(R.id.buttonSeeMore10);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        viewPager = findViewById(R.id.viewPager);

        // Sample images
        List<Integer> imageList = Arrays.asList(
                R.drawable.img_15,
                R.drawable.img_14,
                R.drawable.img_16
        );

        sliderAdapter = new SliderAdapter(this, imageList);
        viewPager.setAdapter(sliderAdapter);

        // Initialize the handler
        sliderHandler = new Handler(Looper.getMainLooper());
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = currentItem + 1;

                if (nextItem >= sliderAdapter.getItemCount()) {
                    nextItem = 0; // Loop back to the first item
                }

                viewPager.setCurrentItem(nextItem, true);
                sliderHandler.postDelayed(this, 3000); // Slide every 3 seconds
            }
        };

        // Start the automatic sliding
        sliderHandler.postDelayed(sliderRunnable, 3000);


        listView = findViewById(R.id.lv1);
        search_bar = findViewById(R.id.searchView);
        // Add items to Array List
        mylist = new ArrayList<>();
        mylist.add("Write a New Name in the Book of Life");
        mylist.add("The Motivational Book");
        mylist.add("Call Me By Your Name");
        mylist.add("The Book with No Name");
        mylist.add("Black Forest");
        mylist.add("Think & Grow Rich");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        listView.setAdapter(adapter);


        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(mylist.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(BookActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        // Set click listeners for the buttons
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, after_login.class);
                startActivity(intent);
            }
        });

        buttonSeeMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);

                // Pass book details to the new activity
                intent.putExtra("title", "Write a New Name in the Book of Life");
                intent.putExtra("author", "Dr. Joshep Murphy");
                intent.putExtra("publisher", "Fingerprint! Publishing");
                intent.putExtra("publicationDate", "1 December 2019");
                intent.putExtra("price", "₹139");
                intent.putExtra("description", "Love is, and all there is, is love. A combined edition of Joseph Murphy’s three illuminating works: Write a New Name in the Book of Life, Love is Freedom, and Wheels of Truth, this book explains how you can make your conscious and subconscious work harmoniously, how you can find your divine companion and ensure bliss in your relationships, and how you can tune yourself with the infinite. An outstanding self-help classic packed with powerful information, it will lead you to the path of joy and peace and help you transform your life.");
                intent.putExtra("bookCover", R.drawable.img);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });

        buttonSeeMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);

                // Pass book details to the new activity
                intent.putExtra("title", "The Motivational Book");
                intent.putExtra("author", "Avinash Kumar and Dirgh Raval");
                intent.putExtra("publisher", "Independently Published");
                intent.putExtra("publicationDate", "4 February 2021");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "People who are highly successful have the ability to motivate themselves. They know what they want, and they go out and get it. They maintain high levels of energy without depending on others for a boost. And they continuously learn and grow, both personally and professionally. In this book, you will find the inspirational tools to become the motivated person you want to be in your life and career. You will also learn to counter the negative forces of procrastination, perfectionism, and carelessness. For many people, this book is a source of hope and encouragement that they too can achieve their goals and dreams.");
                intent.putExtra("bookCover", R.drawable.img_1);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });

        buttonSeeMore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);

                // Pass book details to the new activity
                intent.putExtra("title", "Call Me By Your Name");
                intent.putExtra("author", "André Aciman ");
                intent.putExtra("publisher", "It's novel");
                intent.putExtra("publicationDate", "January 23, 2007");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "Call Me by Your Name is a 2007 coming-of-age novel written by Italian-American writer André Aciman. Set in the 1980s, the novel centers on the sudden and powerful romance that blossoms between student Elio Perlman and visiting American scholar Oliver, chronicling their relationship and the 20 years that follow");
                intent.putExtra("bookCover", R.drawable.img_3);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });

        buttonSeeMore4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "The Book with No Name");
                intent.putExtra("author", "Anonymous");
                intent.putExtra("publisher", "Michael O'Mara Books Ltd");
                intent.putExtra("publicationDate", "1 June 2007");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "Detective Miles Jensen is called to the lawless town of Santa Mondega to investigate a spate of murders. This would all be quite ordinary in those rough streets, except that Jensen is the Chief Detective of Supernatural Investigations. The breakneck plot centers around a mysterious blue stone — The Eye of the Moon — and the men and women who all want to get their hands on a mass murderer with a drinking problem, a hit man who thinks he's Elvis, and a pair of monks among them. Add in the local crime baron, an amnesiac woman who's just emerged from a five-year coma, a gypsy fortune teller, and a hapless hotel porter, and the plot thickens fast. Most importantly, how do all these people come to be linked to the strange book with no name? This is the anonymous, ancient book that no one seems to have survived reading. Everyone who has ever read it has been murdered.");
                intent.putExtra("bookCover", R.drawable.img_6);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });

        buttonSeeMore5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "Black Forest");
                intent.putExtra("author", "Shane Lee");
                intent.putExtra("publisher", "IngramSpark");
                intent.putExtra("publicationDate", "2 November 2020");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "Monty is a young man who is just trying to keep up his family's farm after the death of his father. He's never cared for farm life, but his mother and sister have to eat.\n" +
                        "\n" +
                        "Their farm is on the outskirts of town, near the infamous Dromm Forest. Its trees are black. They never grow, and they never die. There are old tales of a witch who cursed the forest. A silly superstition, but it keeps the townsfolk away.\n" +
                        "\n" +
                        "One day, Monty's younger sister does the unthinkable: she enters the Black Forest. He gets her out safely...but something follows closely behind.\n" +
                        "\n" +
                        "Now, townspeople are dying off one by one. Their skin blackens and they shrivel down to skeletons. Monty is certain it has something to do with those old legends, but no one else is on his side.\n" +
                        "\n");
                intent.putExtra("bookCover", R.drawable.img_7);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });

        buttonSeeMore6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "Think & Grow Rich");
                intent.putExtra("author", "Shane Lee");
                intent.putExtra("publisher", "TarcherPerigee");
                intent.putExtra("publicationDate", "2 November 2020");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "In the original Think and Grow Rich, published in 1937, Hill draws on stories of Andrew Carnegie, Thomas Edison, Henry Ford, and other millionaires of his generation to illustrate his principles. In the updated version, Arthur R. Pell, Ph.D., a nationally known author, lecturer, and consultant in human resources management and an expert in applying Hill's thought, deftly interweaves anecdotes of how contemporary millionaires and billionaires, such as Bill Gates, Mary Kay Ash, Dave Thomas, and Sir John Templeton, achieved their wealth. Outmoded or arcane terminology and examples are faithfully refreshed to preclude any stumbling blocks to a new generation of readers.");
                intent.putExtra("bookCover", R.drawable.img_8);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });
        buttonSeeMore7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "Learning How to Fly: Life Lessons for the Youth");
                intent.putExtra("author", "A. P. J. Abdul Kalam");
                intent.putExtra("publisher", "Rupa Publications India");
                intent.putExtra("publicationDate", "21 October 2016");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "Dr. A. P. J. Abdul Kalam had a great belief in the power of the youth. He met over 21 million children and young people in India and outside and spoke to them about the power of knowledge, ambition, moral behavior and the need to bring about change in society. He travelled to almost every corner of the country meeting the youth in schools, universities and institutions and interacted with them like a committed teacher. In Learning How to Fly some of his nearly 2, 000 lectures have been compiled. These lectures were addressed to teachers and students in school and beyond. In each one of them he has spoken about preparing oneself best for life, to identify and overcome challenges and how to bring out the best within each individual. Through stories from his own life, those of his teachers and mentors as well as stories of some of the greatest men and women of the world and the latest developments in science and technology, he shows us the importance of dreams and the hard work needed to turn those dreams into reality. Filled with warmth, inspiration and a positive attitude, Learning How to Fly is essential reading for every Indian, young and old.");
                intent.putExtra("bookCover", R.drawable.img_10);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });
        buttonSeeMore8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "Love Story from the Heart: Sweet, Clean Romance");
                intent.putExtra("author", "Terry Atkinson");
                intent.putExtra("publisher", "Terry Atkinson Books");
                intent.putExtra("publicationDate", "24 October 2017");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "She guards her damaged heart. He’s ready for a relationship. Will they allow their irresistible attraction a fighting chance?\n" +
                        "Graphic designer Megan Simons dreams of a successful career in the big city of Johannesburg. And after an embarrassing date disaster, the last thing she wants is to give her wounded soul away and divide her focus. But she never expected a tenderhearted guy like Brian to walk into her life.\n" +
                        "\n" +
                        "Financial consultant Brian Phillips longs for a family to call his own. After years of providing for his sisters and ailing mother, he finally gets to strike out on his own to find his special someone. And when he encounters shy and beautiful Megan, he knows it’s love at first sight.\n" +
                        "\n" +
                        "As Megan falls for the charming man, a huge opportunity arises that could skyrocket her ambitions and crush their budding romance. But Brian’s old-fashioned chivalry won’t let her go without a fight.\n" +
                        "\n" +
                        "Can Megan and Brian survive a world of complications to make a future of their own?\n" +
                        "\n" +
                        "A Love Story From the Heart is a sweet and clean romance. If you like doting heroes, startling chemistry, and enchanting African settings, then you’ll adore Terry Atkinson’s captivating tale.");
                intent.putExtra("bookCover", R.drawable.img_11);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });
        buttonSeeMore9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "Embrace the Wind");
                intent.putExtra("author", "Susan Denning");
                intent.putExtra("publisher", "Tstd, LLC/ DBA No Limit Press");
                intent.putExtra("publicationDate", "1 March 2017");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "What is the cost of living life on your own terms? For a young woman on the American Frontier, the price can be high. Aislynn Maher trusted the wrong man. To conceal her disgrace, she abandons her job, her home, her family and friends. She turns to the raw, contentious US marshal of the Wyoming Territory, Orrin Sage, who is hiding a guilty secret of his own. Setting out alone, Aislynn brings her optimism and determination to Cheyenne. But in this rough town, with its prejudice, violence and lawlessness, it’s not just difficult to do what you believe is right─ it's potentially deadly. This unlikely love story details real triumphs and tragedies from frontier women’s letters, diaries and newspapers of the day, and places Aislynn in the center of their history. EMBRACE THE WIND illuminates an intimate picture of the American frontier; the story of the women who changed the West and helped forge the freedoms we enjoy today.");
                intent.putExtra("bookCover", R.drawable.img_13);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });
        buttonSeeMore10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start BookDetailsActivity
                Intent intent = new Intent(BookActivity.this, BookDetailsActivity.class);
                intent.putExtra("title", "Lost and Found ");
                intent.putExtra("author", "Oliver Jeffers");
                intent.putExtra("publisher", "UK Children's");
                intent.putExtra("publicationDate", "J26 February 2015");
                intent.putExtra("price", "₹338");
                intent.putExtra("description", "New cover reissue of this magical story from award-winning international bestselling picture book creator, Oliver Jeffers\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"There once was a boy\\u0085 and one day a penguin arrives on his doorstep. The boy decides the penguin must be lost and tries to return him. But no one seems to be missing a penguin. So the boy decides to take the penguin home himself, and they set out in his row boat on a journey to the South Pole.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"But when they get there, the boy discovers that maybe home wasn\\u0092t what the penguin was looking for after all\\u0085\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"A Children\\u0092s Bookshelf Selection: Each month our editor\\u0092s pick the best books for children and young adults by age to be a part of the children\\u0092s bookshelf. These are editorial recommendations made by our team of experts. Our monthly reading list includes a mix of bestsellers and top new releases and evergreen books that will help enhance a child\\u0092s reading life.");
                intent.putExtra("bookCover", R.drawable.img_12);
                intent.putExtra("email", email); // Pass email to the new activity
                startActivity(intent);
            }
        });
    }

    public String getEmail() {
        return email;
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Stop the slider when the activity is not in the foreground
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart the slider when the activity comes back to the foreground
        sliderHandler.postDelayed(sliderRunnable, 5000);
    }
}
