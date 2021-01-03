package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class StoryActivity extends AppCompatActivity {

    EditText postText;

    Button postButton;

    CheckBox facebookCheck;
    CheckBox twitterCheck;
    CheckBox instagramCheck;

    boolean twitterFlag, facebookFlag, instagramFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        twitterFlag = false;
        facebookFlag = false;
        instagramFlag = false;


        postText = findViewById(R.id.editPostText);
        postButton = findViewById(R.id.postButton);

        facebookCheck = findViewById(R.id.facebookCheckBox);
        twitterCheck = findViewById(R.id.twitterCheckBox);
        instagramCheck = findViewById(R.id.instagramCheckBox);

        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if ( twitterFlag && facebookFlag && instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "All 3 of them Checked", Toast.LENGTH_LONG).show();

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Intent share = new Intent(Intent.ACTION_SEND);

                            // Set the MIME type
                            String type = "image/*";
                            share.setType(type);

                            // Create the URI from the media
                            //File media = new File(mediaPath);
                            //Uri uri = Uri.fromFile(media);
                            Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                            // Add the URI to the Intent.
                            share.putExtra(Intent.EXTRA_STREAM, uri);

                            // Broadcast the Intent.
                            startActivity(Intent.createChooser(share, "Share to"));
                        }
                    });
                    thread.start();
                    try {
                        thread.join();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Set the MIME type
                    String type = "image/*";
                    share.setType(type);

                    // Create the URI from the media
                    //File media = new File(mediaPath);
                    //Uri uri = Uri.fromFile(media);
                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    // Add the URI to the Intent.
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Broadcast the Intent.
                    startActivity(Intent.createChooser(share, "Share to"));

                }
                else if (twitterFlag && facebookFlag){
                    Toast.makeText(StoryActivity.this,
                            "Twitter and Facebook Checked", Toast.LENGTH_LONG).show();

                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Set the MIME type
                    String type = "image/*";
                    share.setType(type);

                    // Create the URI from the media
                    //File media = new File(mediaPath);
                    //Uri uri = Uri.fromFile(media);
                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    // Add the URI to the Intent.
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Broadcast the Intent.
                    startActivity(Intent.createChooser(share, "Share to"));

                }
                else if (twitterFlag && instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "Twitter and Instagram Checked", Toast.LENGTH_LONG).show();

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Intent share = new Intent(Intent.ACTION_SEND);

                            // Set the MIME type
                            String type = "image/*";
                            share.setType(type);

                            // Create the URI from the media
                            //File media = new File(mediaPath);
                            //Uri uri = Uri.fromFile(media);
                            Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                            // Add the URI to the Intent.
                            share.putExtra(Intent.EXTRA_STREAM, uri);

                            // Broadcast the Intent.
                            startActivity(Intent.createChooser(share, "Share to"));
                        }
                    });
                    thread.start();
                    try {
                        thread.join();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Set the MIME type
                    String type = "image/*";
                    share.setType(type);

                    // Create the URI from the media
                    //File media = new File(mediaPath);
                    //Uri uri = Uri.fromFile(media);
                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    // Add the URI to the Intent.
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Broadcast the Intent.
                    startActivity(Intent.createChooser(share, "Share to"));
                }
                else if (facebookFlag && instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "Facebook and Instagram Checked", Toast.LENGTH_LONG).show();

                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Set the MIME type
                    String type = "image/*";
                    share.setType(type);

                    // Create the URI from the media
                    //File media = new File(mediaPath);
                    //Uri uri = Uri.fromFile(media);
                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    // Add the URI to the Intent.
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Broadcast the Intent.
                    startActivity(Intent.createChooser(share, "Share to"));
                }
                else if (twitterFlag){
                    Toast.makeText(StoryActivity.this,
                            "Only Twitter Checked", Toast.LENGTH_LONG).show();

                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Set the MIME type
                    String type = "image/*";
                    share.setType(type);

                    // Create the URI from the media
                    //File media = new File(mediaPath);
                    //Uri uri = Uri.fromFile(media);
                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    // Add the URI to the Intent.
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Broadcast the Intent.
                    startActivity(Intent.createChooser(share, "Share to"));


                }
                else if (facebookFlag){
                    Toast.makeText(StoryActivity.this,
                            "Only Facebook Checked", Toast.LENGTH_LONG).show();
                }
                else if (instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "Only Instagram Checked", Toast.LENGTH_LONG).show();

                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Set the MIME type
                    String type = "image/*";
                    share.setType(type);

                    // Create the URI from the media
                    //File media = new File(mediaPath);
                    //Uri uri = Uri.fromFile(media);
                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    // Add the URI to the Intent.
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Broadcast the Intent.
                    startActivity(Intent.createChooser(share, "Share to"));
                }
                else{
                    Toast.makeText(StoryActivity.this,
                            "None of them Checked", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void onFacebookClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            facebookFlag = true;
        }
        else {
            facebookFlag = false;
        }
    }

    public void onTwitterClicked(View v) throws InterruptedException, TwitterException {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            twitterFlag = true;
        }
        else {
            twitterFlag = false;
        }
    }

    public void onInstagramClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            instagramFlag = true;
        }
        else {
            instagramFlag = false;
        }
    }
}