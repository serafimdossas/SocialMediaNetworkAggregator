package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_main);

        facebookLoginButton = findViewById(R.id.facebookButton);
        textView = findViewById(R.id.tv_name);
        imageView = findViewById(R.id.iv_profilePic);

        callbackManager = CallbackManager.Factory.create();

        // define extra permissions. use the button as it wraps the LoginManager class
        facebookLoginButton.setPermissions(Arrays.asList("user_gender, user_friends"));

        // register the callback manager and handle events
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d("DEMO", "Login Successful!");
                Intent intent = new Intent(FacebookMainActivity.this, HomePage.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d("DEMO", "Login Cancelled!");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("DEMO", "Login Error!");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest graphRequest =  GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("DEMO", object.toString());

                        try {

                            // get name and profile pic
                            String name = object.getString("name");
                            String id = object.getString("id");

                            textView.setText(name);
                            Picasso.get().load("https://graph.facebook.com/" + id + "/picture/")
                                    .into(imageView);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // create bundle
        Bundle bundle = new Bundle();
        bundle.putString("fields", "gender, name, id, first_name, last_name");

        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                LoginManager.getInstance().logOut();
                textView.setText("");
                imageView.setImageResource(0);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        accessTokenTracker.stopTracking();
    }
}