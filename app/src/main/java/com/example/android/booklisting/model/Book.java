package com.example.android.booklisting.model;

/**
 * Created by User on 28.6.2016..
 */
public class Book {

    private String mAuthor;
    private String mTitleDescription;


    public Book(String author, String titleDescription) {
        this.mAuthor = author;
        this.mTitleDescription = titleDescription;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTitleDescription() {
        return mTitleDescription;
    }

    public void setTitleDescription(String mTitleDescription) {
        this.mTitleDescription = mTitleDescription;
    }
}
