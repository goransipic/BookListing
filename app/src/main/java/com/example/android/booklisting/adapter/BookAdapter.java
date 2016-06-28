package com.example.android.booklisting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.booklisting.R;
import com.example.android.booklisting.model.Book;

import java.util.ArrayList;

/**
 * Created by User on 28.6.2016..
 */
public class BookAdapter extends BaseAdapter {

    ArrayList<Book> mBooks;
    Context mContext;

    public BookAdapter(Context context, ArrayList<Book> mBooks) {
        this.mBooks = mBooks;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public Book getItem(int position) {
        return mBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View convertView = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_book, viewGroup, false);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView titleDescription = (TextView) convertView.findViewById(R.id.title_description);

        author.setText(mBooks.get(i).getAuthor());
        titleDescription.setText(mBooks.get(i).getTitleDescription());
        return convertView;
    }
}
