package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.utils.Utils;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;

import okhttp3.MediaType;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.ConfigurationContext;

import static com.example.socialmedianetworkaggregator.TrendingHashtagsActivity.CONSUMER_KEY;
import static com.example.socialmedianetworkaggregator.TrendingHashtagsActivity.CONSUMER_SECRET;
import static com.loopj.android.http.AsyncHttpClient.log;

public class PostActivity extends AppCompatActivity {

    EditText postText;

    Button postButton;

    CheckBox facebookCheck;
    CheckBox twitterCheck;
    CheckBox instagramCheck;

    boolean twitterFlag, facebookFlag, instagramFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);



        twitterFlag = false;
        facebookFlag = false;
        instagramFlag = false;


        postText = findViewById(R.id.editPostText);
        postButton = findViewById(R.id.postButton);

        facebookCheck = findViewById(R.id.facebookCheckBox);
        twitterCheck = findViewById(R.id.twitterCheckBox);
        instagramCheck = findViewById(R.id.instagramCheckBox);


        final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");


        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if ( twitterFlag && facebookFlag && instagramFlag){
                    Toast.makeText(PostActivity.this,
                            "All 3 of them Checked", Toast.LENGTH_LONG).show();
                    String consumerKey = "BS1hJK04QKLT61t6kWmo1UW4P";
                    String consumerSecret = "r9Lp9mIogaVvEBpjBx2grYFEwSm8oKrHKszFaxQMwUtHh7GkAv";
                    String accessToken = "1333402680191045635-DSYeSnH193NWPhV7wa6a5pzq6l0DXf";
                    String accessSecret = "ncw5f3BNGfrreolcywPea6snwIMfX1xM3TLGHsYLq1bZt";

                    ConfigurationBuilder cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(true)
                            .setOAuthConsumerKey(consumerKey)
                            .setOAuthConsumerSecret(consumerSecret)
                            .setOAuthAccessToken(accessToken)
                            .setOAuthAccessTokenSecret(accessSecret);
                    TwitterFactory factory = new TwitterFactory(cb.build());
                    final Twitter twitter = factory.getInstance();
                    //twitter.updateStatus("Hello World!");
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                twitter.updateStatus(String.valueOf(postText.getText()));
                            } catch (TwitterException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Uri imageUri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    Intent shareIntent = new Intent("com.instagram.share.ADD_TO_FEED");
                    shareIntent.setType("image/*");
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.instagram.android");

                    startActivity(shareIntent);


                }
                else if (twitterFlag && facebookFlag){
                    Toast.makeText(PostActivity.this,
                            "Twitter and Facebook Checked", Toast.LENGTH_LONG).show();
                    String consumerKey = "BS1hJK04QKLT61t6kWmo1UW4P";
                    String consumerSecret = "r9Lp9mIogaVvEBpjBx2grYFEwSm8oKrHKszFaxQMwUtHh7GkAv";
                    String accessToken = "1333402680191045635-DSYeSnH193NWPhV7wa6a5pzq6l0DXf";
                    String accessSecret = "ncw5f3BNGfrreolcywPea6snwIMfX1xM3TLGHsYLq1bZt";

                    ConfigurationBuilder cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(true)
                            .setOAuthConsumerKey(consumerKey)
                            .setOAuthConsumerSecret(consumerSecret)
                            .setOAuthAccessToken(accessToken)
                            .setOAuthAccessTokenSecret(accessSecret);
                    TwitterFactory factory = new TwitterFactory(cb.build());
                    final Twitter twitter = factory.getInstance();
                    //twitter.updateStatus("Hello World!");
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                twitter.updateStatus(String.valueOf(postText.getText()));
                            } catch (TwitterException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if (twitterFlag && instagramFlag){
                    Toast.makeText(PostActivity.this,
                            "Twitter and Instagram Checked", Toast.LENGTH_LONG).show();
                    String consumerKey = "BS1hJK04QKLT61t6kWmo1UW4P";
                    String consumerSecret = "r9Lp9mIogaVvEBpjBx2grYFEwSm8oKrHKszFaxQMwUtHh7GkAv";
                    String accessToken = "1333402680191045635-DSYeSnH193NWPhV7wa6a5pzq6l0DXf";
                    String accessSecret = "ncw5f3BNGfrreolcywPea6snwIMfX1xM3TLGHsYLq1bZt";

                    ConfigurationBuilder cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(true)
                            .setOAuthConsumerKey(consumerKey)
                            .setOAuthConsumerSecret(consumerSecret)
                            .setOAuthAccessToken(accessToken)
                            .setOAuthAccessTokenSecret(accessSecret);
                    TwitterFactory factory = new TwitterFactory(cb.build());
                    final Twitter twitter = factory.getInstance();
                    //twitter.updateStatus("Hello World!");
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                twitter.updateStatus(String.valueOf(postText.getText()));
                            } catch (TwitterException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Uri imageUri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    Intent shareIntent = new Intent("com.instagram.share.ADD_TO_FEED");
                    shareIntent.setType("image/*");
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.instagram.android");

                    startActivity(shareIntent);
                }
                else if (facebookFlag && instagramFlag){
                    Toast.makeText(PostActivity.this,
                            "Facebook and Instagram Checked", Toast.LENGTH_LONG).show();


                    Uri imageUri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    Intent shareIntent = new Intent("com.instagram.share.ADD_TO_FEED");
                    shareIntent.setType("image/*");
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.instagram.android");

                    startActivity(shareIntent);

                }
                else if (twitterFlag){
                    Toast.makeText(PostActivity.this,
                            "Only Twitter Checked", Toast.LENGTH_LONG).show();
                    String consumerKey = "BS1hJK04QKLT61t6kWmo1UW4P";
                    String consumerSecret = "r9Lp9mIogaVvEBpjBx2grYFEwSm8oKrHKszFaxQMwUtHh7GkAv";
                    String accessToken = "1333402680191045635-DSYeSnH193NWPhV7wa6a5pzq6l0DXf";
                    String accessSecret = "ncw5f3BNGfrreolcywPea6snwIMfX1xM3TLGHsYLq1bZt";

                    ConfigurationBuilder cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(true)
                            .setOAuthConsumerKey(consumerKey)
                            .setOAuthConsumerSecret(consumerSecret)
                            .setOAuthAccessToken(accessToken)
                            .setOAuthAccessTokenSecret(accessSecret);
                    TwitterFactory factory = new TwitterFactory(cb.build());
                    final Twitter twitter = factory.getInstance();
                    //twitter.updateStatus("Hello World!");
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                twitter.updateStatus(String.valueOf(postText.getText()));
                            } catch (TwitterException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if (facebookFlag){
                    Toast.makeText(PostActivity.this,
                            "Only Facebook Checked", Toast.LENGTH_LONG).show();
                }
                else if (instagramFlag){
                    Toast.makeText(PostActivity.this,
                            "Only Instagram Checked!", Toast.LENGTH_LONG).show();

                    Uri imageUri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    Intent shareIntent = new Intent("com.instagram.share.ADD_TO_FEED");
                    shareIntent.setType("image/*");
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.instagram.android");

                    startActivity(shareIntent);
                }
                else{
                    Toast.makeText(PostActivity.this,
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
        if (!checkBox.isChecked()){
            facebookFlag = false;
        }
    }

    public void onTwitterClicked(View v) throws InterruptedException, TwitterException {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            twitterFlag = true;
        }
        if (!checkBox.isChecked()){
            twitterFlag = false;
        }
    }

    public void onInstagramClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            instagramFlag = true;
        }
        if (!checkBox.isChecked()){
            instagramFlag = false;
        }
    }

    public void createInstagramIntent(String type, String mediaPath){

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }
}