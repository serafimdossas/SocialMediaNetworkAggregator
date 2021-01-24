package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.loopj.android.http.AsyncHttpClient.log;

public class HashtagsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtags);

        final EditText hashtagTextSearch;
        hashtagTextSearch = (EditText) findViewById(R.id.hashtagTextSearch);

        Button searchButton;
        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HashtagsActivity.this, SearchedHashtagsActivity.class);
                i.putExtra("EXTRA_HASHTAG", hashtagTextSearch.getText().toString());
                startActivity(i);
            }
        });


        Button trendsButton;
        trendsButton = findViewById(R.id.trendsButton);
        trendsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HashtagsActivity.this,TrendingHashtagsActivity.class);
                startActivity(i);
            }
        });

    }
}