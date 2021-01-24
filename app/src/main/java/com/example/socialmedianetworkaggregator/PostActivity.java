package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.utils.Utils;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
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

    private Button postButton;

    private CheckBox facebookCheck;
    private CheckBox twitterCheck;
    private CheckBox instagramCheck;


    private ShareButton sharePhotoButton;
    private ShareButton shareLinkButton;
    private ImageView image;

    private boolean twitterFlag, facebookFlag, instagramFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);



        twitterFlag = false;
        facebookFlag = false;
        instagramFlag = false;


        sharePhotoButton = (ShareButton) findViewById(R.id.bt_sharePhoto);
        shareLinkButton = findViewById(R.id.bt_shareLink);
        image = (ImageView) findViewById(R.id.iv_picture);
        image.setImageResource(R.drawable.story);

        postButton = (Button) findViewById(R.id.postButton);

        facebookCheck = (CheckBox) findViewById(R.id.facebookCheckBox);
        twitterCheck = (CheckBox) findViewById(R.id.twitterCheckBox);
        instagramCheck = (CheckBox) findViewById(R.id.instagramCheckBox);

        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse(
                "https://www.youtube.com/watch?v=GxrxV37a9YE"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#success").build()).build();

        shareLinkButton.setShareContent(shareLinkContent);

        Drawable drawable = image.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();

        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

        sharePhotoButton.setShareContent(sharePhotoContent);


        final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");


        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if (twitterFlag){
                    Toast.makeText(PostActivity.this,
                            "Twitter Checked", Toast.LENGTH_LONG).show();


                    final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                            .getActiveSession();

                    StrictMode.VmPolicy.Builder photoBuilder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(photoBuilder.build());
                    Uri imageUri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    TweetComposer.Builder builder = new TweetComposer.Builder(PostActivity.this)
                            .text("")
                            .image(imageUri);
                    builder.show();


                }
                if (facebookFlag){
                    Toast.makeText(PostActivity.this,
                            "Facebook Checked", Toast.LENGTH_LONG).show();

                    ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse(
                            "https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                            .setShareHashtag(new ShareHashtag.Builder()
                                    .setHashtag("#success").build()).build();

                    shareLinkButton.setShareContent(shareLinkContent);

                    Drawable drawable = image.getDrawable();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    SharePhoto sharePhoto = new SharePhoto.Builder()
                            .setBitmap(bitmap)
                            .build();

                    SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                            .addPhoto(sharePhoto)
                            .build();

                    sharePhotoButton.setShareContent(sharePhotoContent);

                }
                if (instagramFlag){
                    Toast.makeText(PostActivity.this,
                            "Instagram Checked!", Toast.LENGTH_LONG).show();

                    Uri imageUri = Uri.parse("file:///storage/emulated/0/Download/download.png");

                    Intent shareIntent = new Intent("com.instagram.share.ADD_TO_FEED");
                    shareIntent.setType("image/*");
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.instagram.android");

                    startActivity(shareIntent);
                }
            }
        });

    }

    public void onFacebookClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            facebookFlag = true;
            sharePhotoButton.setVisibility(View.VISIBLE);
            shareLinkButton.setVisibility(View.VISIBLE);
            // prepare photo to be shared when the Share Photo button is pressed
        }
        if (!checkBox.isChecked()){
            facebookFlag = false;
            sharePhotoButton.setVisibility(View.INVISIBLE);
            shareLinkButton.setVisibility(View.INVISIBLE);

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

}