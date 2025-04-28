package com.example.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_BOOK = "books";
    private static final String TABLE_PURCHASED_BOOKS = "purchased_books";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_MOBILE_NO = "mobile_no";
    private static final String COLUMN_PURCHASED_BOOKS = "purchased_books";

    private static final String COLUMN_BOOK_TITLE = "title";
    private static final String COLUMN_BOOK_AUTHOR = "author";

    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_PUBLISHER = "publisher";
    private static final String COLUMN_PUBLICATION_DATE = "publication_date";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_BOOK_COVER = "book_cover";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_MOBILE_NO + " TEXT,"
                + COLUMN_PURCHASED_BOOKS + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_PURCHASED_BOOKS_TABLE = "CREATE TABLE " + TABLE_PURCHASED_BOOKS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_PUBLISHER + " TEXT,"
                + COLUMN_PUBLICATION_DATE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_BOOK_COVER + " INTEGER" + ")";
        db.execSQL(CREATE_PURCHASED_BOOKS_TABLE);

        String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_AUTHOR + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_BOOK);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASED_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    public boolean addUser(String name, String email, String password, String mobileNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_MOBILE_NO, mobileNo);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    // Method to add a book to the TABLE_BOOK
    public boolean addBook(String title, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_AUTHOR, author);

        // Inserting Row
        db.insert(TABLE_BOOK, null, values);
        db.close(); // Closing database connection
        return false;
    }
    // Method to search books by title
    public String searchBooksByTitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOK, null, COLUMN_TITLE + " LIKE ?", new String[]{"%" + title + "%"}, null, null, null);

        StringBuilder result = new StringBuilder();

        if (cursor.moveToFirst()) {
            do {
                String bookTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String bookAuthor = cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR));

                result.append("Title: ").append(bookTitle).append("\n")
                        .append("Author: ").append(bookAuthor).append("\n");
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result.toString();
    }

    // Method to insert a purchased book
    public boolean insertPurchasedBook(String email, String title, String author, String publisher, String publicationDate, String description, int bookCover) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("publisher", publisher);
        contentValues.put("publication_date", publicationDate);
        contentValues.put("description", description);
        contentValues.put("book_cover", bookCover);

        Log.d("DatabaseHelper", "Inserting book: " + title + ", email: " + email);

        long result = db.insert("purchased_books", null, contentValues);
        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert book: " + title);
            return false;
        } else {
            Log.d("DatabaseHelper", "Book inserted successfully: " + title);
            return true;
        }
    }
    public Cursor getPurchasedBooksByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT title, author FROM " + TABLE_PURCHASED_BOOKS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
    }

    public boolean updateUser(String email, String name, String password, String mobileNo) {
        if (email == null || name == null || password == null || mobileNo == null) {
            Log.e("DatabaseHelper", "One of the required fields is null");
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("mobile_no", mobileNo);

        int rowsAffected = db.update("users", contentValues, "email = ?", new String[]{email});
        return rowsAffected > 0;
    }

    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?", new String[]{email}, null, null, null);
    }
}
