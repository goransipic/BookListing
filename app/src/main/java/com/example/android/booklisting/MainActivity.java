package com.example.android.booklisting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.booklisting.adapter.BookAdapter;
import com.example.android.booklisting.loader.DownloadTask;
import com.example.android.booklisting.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    TextInputLayout mTextInputLayout;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInputLayout = (TextInputLayout) findViewById(R.id.input_text);
        mButton = (Button) findViewById(R.id.button_search);

        mListView = (ListView) findViewById(R.id.list_view);


        mButton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           new AsyncTask<String, Void, ArrayList<Book>>() {

                                               @Override
                                               protected ArrayList<Book> doInBackground(String... urls) {

                                                   ArrayList<Book> bookArrayList = new ArrayList<Book>();


                                                   try {

                                                       JSONObject jsonObj = null;
                                                       JSONArray jsonArray = null;
                                                       int totalItems = 0;

                                                       String urlResult = null;

                                                       urlResult = DownloadTask.loadFromNetwork(urls[0]);

                                                       jsonObj = new JSONObject(urlResult);
                                                       if (jsonObj.has("items")) {
                                                           jsonArray = jsonObj.getJSONArray("items");
                                                       }
                                                       totalItems = jsonObj.getInt("totalItems");

                                                       if (totalItems == 0) {
                                                           return bookArrayList;
                                                       }

                                                       for (int i = 0; i < jsonArray.length(); i++) {
                                                           JSONObject jsonObject = jsonArray.getJSONObject(i);

                                                           JSONObject bookObject = jsonObject.getJSONObject("volumeInfo");

                                                           Book book = new Book(null, null);

                                                           book.setAuthor(bookObject.getString("title"));

                                                           JSONArray bookAuthors = null;

                                                           if (bookObject.has("authors")) {
                                                               bookAuthors = bookObject.getJSONArray("authors");
                                                           }


                                                           if (bookAuthors != null) {

                                                               String authors = "";
                                                               for (int j = 0; j < bookAuthors.length(); j++) {

                                                                   if (bookAuthors.length() > 1) {

                                                                       if (j == 0) {
                                                                           authors += bookAuthors.getString(j);
                                                                       } else {
                                                                           authors += ", " + bookAuthors.getString(j);
                                                                       }
                                                                   } else {
                                                                       authors += bookAuthors.getString(j);
                                                                   }

                                                               }


                                                               book.setTitleDescription(authors);

                                                               bookArrayList.add(book);
                                                           } else {
                                                               bookArrayList.add(book);
                                                           }

                                                       }
                                                   } catch (IOException | JSONException e) {
                                                       return null;
                                                   }


                                                   return bookArrayList;
                                               }

                                               @Override
                                               protected void onPostExecute(ArrayList<Book> books) {

                                                   if (books != null) { // there is not any  network exception
                                                       mListView.setEmptyView(findViewById(R.id.error_view));
                                                       mListView.setAdapter(new BookAdapter(MainActivity.this, books));
                                                   } else {
                                                       Toast.makeText(MainActivity.this,MainActivity.this.getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                                                   }


                                               }
                                           }.execute(Config.baseUrl + "?q=" + (mTextInputLayout.getEditText().getText().length() > 0 ? mTextInputLayout.getEditText().getText() : "\" \""));
                                       }
                                   }

        );

    }


}
