package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchedHashtagsActivity extends AppCompatActivity {

    private String hashtagId;
    private AccessToken accessToken;

    private final static String SEARCHED_HASHTAGS_TAG = "Searched Hashtags Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_hashtag);

        String hashtag = getIntent().getStringExtra("EXTRA_HASHTAG");
        final TextView textPosts;
        textPosts= (TextView) findViewById(R.id.textPosts);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query(hashtag)
                .maxItemsPerRequest(50)
                .build();

        final TweetTimelineRecyclerViewAdapter adapter =
                new TweetTimelineRecyclerViewAdapter.Builder(this)
                        .setTimeline(searchTimeline)
                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                        .build();

        recyclerView.setAdapter((RecyclerView.Adapter) adapter);

        if(hashtag.contains(" ")){
            Toast.makeText(SearchedHashtagsActivity.this, "You cant select hashtag with 2 words!", Toast.LENGTH_LONG).show();
        }
        else{

            GraphRequest hashtagIdRequest = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/ig_hashtag_search",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            Log.d(SEARCHED_HASHTAGS_TAG, "Fetched Hashtag ID: " + response.getJSONObject().toString());

                            try {
                                JSONObject hashtagJson = response.getJSONObject();

                                hashtagId = ((JSONArray) response.getJSONObject().get("data"))
                                        .getJSONObject(0)
                                        .getString("id");


                                Log.d(SEARCHED_HASHTAGS_TAG, hashtagId);
                            } catch (JSONException e){
                                Log.d(SEARCHED_HASHTAGS_TAG, "Error during the reading of the hashtag id");
                            }

                            GraphRequest topHashtagsRequests = GraphRequest.newGraphPathRequest(
                                    AccessToken.getCurrentAccessToken(),
                                    "/" + hashtagId + "/top_media",
                                    new GraphRequest.Callback() {
                                        @Override
                                        public void onCompleted(GraphResponse response) {
                                            Log.d(SEARCHED_HASHTAGS_TAG, response.toString());
                                        }
                                    });

                            Bundle parameters = new Bundle();
                            parameters.putString("user_id", "17841444624927306");
                            topHashtagsRequests.setParameters(parameters);
                            topHashtagsRequests.executeAsync();
                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("user_id", "17841444624927306");
            parameters.putString("q", hashtag.replace("#", ""));
            hashtagIdRequest.setParameters(parameters);
            hashtagIdRequest.executeAsync();
        }

    }
}