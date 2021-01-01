package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.com_twitter_sdk_android_TWITTER_CONSUMER_KEY), getResources().getString(R.string.com_twitter_sdk_android_TWITTER_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);

        setContentView(R.layout.activity_main);

        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                String twitterName = session.getUserName();
                Log.i("Class: ", "Twitter token and secret: " + token +"\t"+ secret);
                Log.i("Class: ", "Twitter name: " + twitterName);

                twitterLogin(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void twitterLogin (TwitterSession session){
        String username = session.getUserName();
        Intent intent = new Intent(MainActivity.this, FacebookMainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

}
