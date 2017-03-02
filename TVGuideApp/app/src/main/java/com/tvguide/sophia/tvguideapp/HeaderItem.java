package com.tvguide.sophia.tvguideapp;

import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class HeaderItem implements ListItem {
    private String mHeader;

    public HeaderItem(String s) {
        mHeader = s;
    }

    @Override
    public int getViewType() {
        return ListItemType.TYPE_HEADER.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.header, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        TextView text = (TextView) view.findViewById(R.id.separator);
        text.setText(mHeader);

        return view;
    }
}
