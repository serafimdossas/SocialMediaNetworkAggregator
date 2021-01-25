package com.example.socialmedianetworkaggregator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import static com.loopj.android.http.AsyncHttpClient.log;

public class TrendingHashtagsActivity extends AppCompatActivity {

    private static final String TAG = "Trends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_hashtags);

        ListView trendsList = findViewById(R.id.trendsList);
        final ArrayList<String> trendsArray = new ArrayList<String>();

        final Trends[] trends = new Trends[1];

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(getResources().getString(R.string.com_twitter_sdk_android_TWITTER_CONSUMER_KEY))
                .setOAuthConsumerSecret(getResources().getString(R.string.com_twitter_sdk_android_TWITTER_CONSUMER_SECRET))
                .setOAuthAccessToken(getResources().getString(R.string.com_twitter_sdk_android_TWITTER_ACCESS_TOKEN))
                .setOAuthAccessTokenSecret(getResources().getString(R.string.com_twitter_sdk_android_TWITTER_ACCESS_SECRET));

        TwitterFactory tf = new TwitterFactory(cb.build());
        final Twitter twitter = tf.getInstance();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    try {
                        trends[0] = twitter.getPlaceTrends(23424833);
                        int k =0;
                        String text = "---";
                        for (Trend trend : trends[0].getTrends()){
                            text = trend.getName();
                            trendsArray.add(text);
                            log.e(TAG, trendsArray.get(k));
                            k++;
                            if (k>19) break;
                        }
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TrendingHashtagsActivity.this, android.R.layout.simple_list_item_1, trendsArray);
            trendsList.setAdapter(arrayAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(TrendingHashtagsActivity.this, SearchedHashtagsActivity.class);
                String selected = (String) adapterView.getItemAtPosition(i);
                j.putExtra("EXTRA_HASHTAG", selected);
                startActivity(j);
            }
        });

    }
}