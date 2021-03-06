package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

import java.io.File;

import okhttp3.MediaType;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import static com.loopj.android.http.AsyncHttpClient.log;

public class StoryActivity extends AppCompatActivity {

    private Button postButton;

    private CheckBox facebookCheck;
    private CheckBox twitterCheck;
    private CheckBox instagramCheck;


    private ShareButton sharePhotoButton;
    private ShareButton shareLinkButton;
    private ImageView image;

    private final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    private boolean twitterFlag, facebookFlag, instagramFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        twitterFlag = false;
        facebookFlag = false;
        instagramFlag = false;


        postButton = findViewById(R.id.postButton);

        facebookCheck = (CheckBox) findViewById(R.id.facebookCheckBox);
        twitterCheck = (CheckBox) findViewById(R.id.twitterCheckBox);
        instagramCheck = (CheckBox) findViewById(R.id.instagramCheckBox);

        sharePhotoButton = (ShareButton) findViewById(R.id.bt_sharePhoto);
        shareLinkButton = findViewById(R.id.bt_shareLink);
        image = (ImageView) findViewById(R.id.iv_picture);
        image.setImageResource(R.drawable.story);

        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if (twitterFlag){
                    Toast.makeText(StoryActivity.this,
                            "Twitter Checked", Toast.LENGTH_LONG).show();

                    Uri uri = Uri.parse("drawable/story.jpg");

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
                if (facebookFlag){
                    Toast.makeText(StoryActivity.this,
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
                    Toast.makeText(StoryActivity.this,
                            "Instagram Checked", Toast.LENGTH_LONG).show();

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