package com.tvguide.sophia.tvguideapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

class SchedulesTask extends AsyncTask {

    private Context mContext;
    private ListView mList;
    private String mUrl;

    public SchedulesTask (Context context, String url, ListView list) {
        mContext = context;
        mList = list;
        mUrl = url;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            URLConnection connection = new URL(mUrl).openConnection();
            InputStream response = connection.getInputStream();

            BufferedReader bReader = new BufferedReader(new InputStreamReader(response));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = bReader.readLine()) != null) {
                builder.append(line);
            }

            JSONArray jObj = new JSONArray(builder.toString());
            return jObj;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        List<ListItem> data = this.extractDataFromJSONObject(o);

        SectionListAdapter adapter = new SectionListAdapter(mContext, data);
        mList.setAdapter(adapter);
    }

    private List<ListItem> extractDataFromJSONObject(Object o) {
        JSONArray jsonArr = (JSONArray) o;
        List<ListItem> data = new ArrayList<ListItem>();
        for (int i = 0; i < jsonArr.length(); i++) {
            try {
                JSONObject current = (JSONObject) jsonArr.get(i);
                data.add(new HeaderItem(current.getString("NameOfTV")));
                JSONArray entries = current.getJSONArray("Series");
                for (int j = 0; j < entries.length(); j++) {
                    JSONObject currentEntry = (JSONObject) entries.get(j);
                    data.add(new EntryItem(currentEntry.getString("Time"), currentEntry.getString("Name")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}