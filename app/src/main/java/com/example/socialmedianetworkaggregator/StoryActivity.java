package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import static com.loopj.android.http.AsyncHttpClient.log;

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

                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    try {
                        StoryActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                    Intent intentTwitter = new Intent(Intent.ACTION_SEND);

                    intentTwitter.setClassName("com.twitter.android", "com.twitter.app.fleets.page.FleetThreadActivity");
                    intentTwitter.setType("image/*");
                    intentTwitter.putExtra(Intent.EXTRA_TEXT, uri);

                    startActivity(intentTwitter);


                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intentInsta = new Intent("com.instagram.share.ADD_TO_STORY");
                    intentInsta.putExtra("source_application", sourceApplication);

                    intentInsta.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intentInsta.putExtra("interactive_asset_uri", stickerAssetUri);
                    intentInsta.putExtra("top_background_color", "#33FF33");
                    intentInsta.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intentInsta, 0) != null) {
                        activity.startActivityForResult(intentInsta, 0);
                    }

                }
                else if (twitterFlag && facebookFlag){
                    Toast.makeText(StoryActivity.this,
                            "Twitter and Facebook Checked", Toast.LENGTH_LONG).show();

                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    try {
                        StoryActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                    Intent intentTwitter = new Intent(Intent.ACTION_SEND);

                    intentTwitter.setClassName("com.twitter.android", "com.twitter.app.fleets.page.FleetThreadActivity");
                    intentTwitter.setType("image/*");
                    intentTwitter.putExtra(Intent.EXTRA_TEXT, uri);

                    startActivity(intentTwitter);

                }
                else if (twitterFlag && instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "Twitter and Instagram Checked", Toast.LENGTH_LONG).show();

                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    try {
                        StoryActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                    Intent intentTwitter = new Intent(Intent.ACTION_SEND);

                    intentTwitter.setClassName("com.twitter.android", "com.twitter.app.fleets.page.FleetThreadActivity");
                    intentTwitter.setType("image/*");
                    intentTwitter.putExtra(Intent.EXTRA_TEXT, uri);

                    startActivity(intentTwitter);

                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intentInsta = new Intent("com.instagram.share.ADD_TO_STORY");
                    intentInsta.putExtra("source_application", sourceApplication);

                    intentInsta.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intentInsta.putExtra("interactive_asset_uri", stickerAssetUri);
                    intentInsta.putExtra("top_background_color", "#33FF33");
                    intentInsta.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intentInsta, 0) != null) {
                        activity.startActivityForResult(intentInsta, 0);
                    }
                }
                else if (facebookFlag && instagramFlag){
                    Toast.makeText(StoryActivity.this,
                            "Facebook and Instagram Checked", Toast.LENGTH_LONG).show();

                    Uri stickerAssetUri = Uri.parse("file:///storage/emulated/0/Download/download.png");
                    String sourceApplication = "com.example.socialmedianetworkaggregator";

                    // Instantiate implicit intent with ADD_TO_STORY action,
                    // sticker asset, and background colors
                    Intent intentInsta = new Intent("com.instagram.share.ADD_TO_STORY");
                    intentInsta.putExtra("source_application", sourceApplication);

                    intentInsta.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intentInsta.putExtra("interactive_asset_uri", stickerAssetUri);
                    intentInsta.putExtra("top_background_color", "#33FF33");
                    intentInsta.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intentInsta, 0) != null) {
                        activity.startActivityForResult(intentInsta, 0);
                    }
                }
                else if (twitterFlag){
                    Toast.makeText(StoryActivity.this,
                            "Only Twitter Checked", Toast.LENGTH_LONG).show();

                    Uri uri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    try {
                        StoryActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                    Intent intentTwitter = new Intent(Intent.ACTION_SEND);

                    intentTwitter.setClassName("com.twitter.android", "com.twitter.app.fleets.page.FleetThreadActivity");
                    intentTwitter.setType("image/*");
                    intentTwitter.putExtra(Intent.EXTRA_TEXT, uri);

                    startActivity(intentTwitter);
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
                    Intent intentInsta = new Intent("com.instagram.share.ADD_TO_STORY");
                    intentInsta.putExtra("source_application", sourceApplication);

                    intentInsta.setType(String.valueOf(MEDIA_TYPE_JPEG));
                    intentInsta.putExtra("interactive_asset_uri", stickerAssetUri);
                    intentInsta.putExtra("top_background_color", "#33FF33");
                    intentInsta.putExtra("bottom_background_color", "#FF00FF");

                    // Instantiate activity and verify it will resolve implicit intent
                    Activity activity = StoryActivity.this;
                    activity.grantUriPermission(
                            "com.instagram.android", stickerAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (activity.getPackageManager().resolveActivity(intentInsta, 0) != null) {
                        activity.startActivityForResult(intentInsta, 0);
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