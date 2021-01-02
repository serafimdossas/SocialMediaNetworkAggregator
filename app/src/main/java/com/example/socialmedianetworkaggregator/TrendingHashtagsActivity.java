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

    public static final String CONSUMER_KEY = "BS1hJK04QKLT61t6kWmo1UW4P";
    public static final String CONSUMER_SECRET = "r9Lp9mIogaVvEBpjBx2grYFEwSm8oKrHKszFaxQMwUtHh7GkAv";

    public static final String TAG = "TwitterUtils";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_hashtags);

        ListView trendsList = findViewById(R.id.trendsList);
        final ArrayList<String> trendsArray = new ArrayList<String>();

        final Trends[] trends = new Trends[1];

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken("1333402680191045635-DSYeSnH193NWPhV7wa6a5pzq6l0DXf")
                .setOAuthAccessTokenSecret("ncw5f3BNGfrreolcywPea6snwIMfX1xM3TLGHsYLq1bZt");

        TwitterFactory tf = new TwitterFactory(cb.build());
        final Twitter twitter = tf.getInstance();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    try {
                        trends[0] = twitter.getPlaceTrends(23424833);
                        int k =0;
                        log.e(TAG, "line 116");
                        String text = "---";
                        for (Trend trend : trends[0].getTrends()){
                            text = trend.getName();
                            trendsArray.add(text);
                            log.e(TAG, trendsArray.get(k));
                            k++;
                            if (k>19) break;
                        }
                        log.e(TAG, "line 130 "+ k + " fores");
                    } catch (TwitterException e) {
                        log.e(TAG, "line 125");
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
            log.e(TAG, "line 127");
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