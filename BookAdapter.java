package com.example.bookstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_book_detail, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.imageViewBookCover.setImageResource(book.getCoverImage());
        holder.textViewBookTitle.setText(book.getTitle());
        holder.textViewBookAuthor.setText(book.getAuthor());

        holder.buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConfirmBuyActivity.class);
                intent.putExtra("book_id", book.getId());
                Intent email = intent.putExtra("email", ((BookActivity) context).getEmail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void filter(String newText) {
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewBookCover;
        TextView textViewBookTitle;
        TextView textViewBookAuthor;
        Button buttonBuy;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBookCover = itemView.findViewById(R.id.imageViewBookCover);
            textViewBookTitle = itemView.findViewById(R.id.textViewTitle);
            textViewBookAuthor = itemView.findViewById(R.id.textViewAuthor);
            buttonBuy = itemView.findViewById(R.id.buttonbuy);
        }
    }
}
