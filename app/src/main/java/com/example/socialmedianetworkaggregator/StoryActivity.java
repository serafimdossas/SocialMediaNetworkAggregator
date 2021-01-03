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

import okhttp3.MediaType;
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

    final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

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

                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                    intent.putExtra("source_application", sourceApplication);

                    intent.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intent.putExtra("interactive_asset_uri", stickerAssetUri);
                    intent.putExtra("top_background_color", "#33FF33");
                    intent.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
                        activity.startActivityForResult(intent, 0);
                    }

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

                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                    intent.putExtra("source_application", sourceApplication);

                    intent.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intent.putExtra("interactive_asset_uri", stickerAssetUri);
                    intent.putExtra("top_background_color", "#33FF33");
                    intent.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
                        activity.startActivityForResult(intent, 0);
                    }
                }
                else if (facebookFlag && instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "Facebook and Instagram Checked", Toast.LENGTH_LONG).show();

                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                    intent.putExtra("source_application", sourceApplication);

                    intent.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intent.putExtra("interactive_asset_uri", stickerAssetUri);
                    intent.putExtra("top_background_color", "#33FF33");
                    intent.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
                        activity.startActivityForResult(intent, 0);
                    }
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

                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                    intent.putExtra("source_application", sourceApplication);

                    intent.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intent.putExtra("interactive_asset_uri", stickerAssetUri);
                    intent.putExtra("top_background_color", "#33FF33");
                    intent.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
                        activity.startActivityForResult(intent, 0);
                    }

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