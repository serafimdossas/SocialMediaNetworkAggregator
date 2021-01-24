package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_hashtag);

        String hashtag = getIntent().getStringExtra("EXTRA_HASHTAG");
        final TextView textPosts;
        textPosts=findViewById(R.id.textPosts);

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

        GraphRequest hashtagIdRequest = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/ig_hashtag_search",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("DEMO", "Fetched Hashtag ID: " + response.getJSONObject().toString());

                        try {
                            JSONObject hashtagJson = response.getJSONObject();

                            hashtagId = ((JSONArray) response.getJSONObject().get("data"))
                                    .getJSONObject(0)
                                    .getString("id");


                            Log.d("DEMO", hashtagId);
                        } catch (JSONException e){
                            Log.d("DEMO", "Error during the reading of the hashtag id");
                        }

                        GraphRequest topHashtagsRequests = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/" + hashtagId + "/top_media",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        Log.d("DEMO", response.toString());
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