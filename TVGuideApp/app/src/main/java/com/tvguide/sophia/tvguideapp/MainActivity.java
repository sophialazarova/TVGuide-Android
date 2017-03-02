package com.tvguide.sophia.tvguideapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void categorySelected(View view) {
        String tag = (String)view.getTag();
        String url = "";

        switch (tag) {
            case "series" : url = "http://tvguide-2.apphb.com/api/tvguide/GetTVSeriesSchedule?date=2017-03-02";
                break;
            case "tv" : url="http://tvguide-2.apphb.com/api/tvguide/GetMoviesSchedule?date=2017-03-02";
                break;
            case "movies": url = "http://tvguide-2.apphb.com/api/tvguide/GetMoviesSchedule?date=2017-03-02";
                break;
            case "sports" : url = "http://tvguide-2.apphb.com/api/tvguide/GetSportsSchedule?date=2017-03-02";
                break;

        }

        Intent i = new Intent(getBaseContext(), CategoryActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("url", url);
        getBaseContext().startActivity(i);
    }
}


