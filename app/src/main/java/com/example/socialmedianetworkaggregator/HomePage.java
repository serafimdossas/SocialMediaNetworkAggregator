package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button postButton = (Button) findViewById(R.id.buttonPost);
        Button storyButton = (Button) findViewById(R.id.buttonStory);
        Button hashtagsButton = (Button) findViewById(R.id.buttonHashtags);

        hashtagsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses Hashtags button
                Intent i = new Intent(HomePage.this, HashtagsActivity.class);
                startActivity(i);

            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses Post button
                Intent i = new Intent(HomePage.this, PostActivity.class);
                startActivity(i);

            }
        });

        storyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses Story button
                Intent i = new Intent(HomePage.this, StoryActivity.class);
                startActivity(i);

            }
        });



    }
}