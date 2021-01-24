package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FacebookMainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private Button continueButton;

    private final static String FACEBOOK_LOGIN_TAG = "FACEBOOK_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_main);

        facebookLoginButton = findViewById(R.id.facebookButton);
        continueButton = (Button) findViewById(R.id.continue_bt);
        if (AccessToken.getCurrentAccessToken() == null){
            continueButton.setEnabled(false);}
        else{
            continueButton.setEnabled(true);
        }

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacebookMainActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

        callbackManager = CallbackManager.Factory.create();

        // define extra permissions. use the button as it wraps the LoginManager class
        facebookLoginButton.setPermissions(Arrays.asList("user_gender, user_friends"));

        // register the callback manager and handle events
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d(FACEBOOK_LOGIN_TAG, "Login Successful!");


            }

            @Override
            public void onCancel() {
                Log.d(FACEBOOK_LOGIN_TAG, "Login Cancelled!");
                Toast.makeText(FacebookMainActivity.this, "Login cancelled.", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(FACEBOOK_LOGIN_TAG, "Login Error!");
                Toast.makeText(FacebookMainActivity.this, "Login failed!.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                LoginManager.getInstance().logOut();
                continueButton.setEnabled(false);
            }
            else{
                continueButton.setEnabled(true);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        accessTokenTracker.stopTracking();
    }
}
