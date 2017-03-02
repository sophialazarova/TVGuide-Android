package com.tvguide.sophia.tvguideapp;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class EntryItem implements ListItem {
    private String mTitle;
    private String mText;

    public EntryItem(String str, String str1) {
        mText = str1;
        mTitle = str;
    }

    @Override
    public int getViewType() {
        return ListItemType.TYPE_ENTRY.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.list_item, null);
            // Do some initialization
        } else {
            view = convertView;
        }

        TextView text1 = (TextView) view.findViewById(R.id.list_content1);
        TextView text2 = (TextView) view.findViewById(R.id.list_content2);
        text1.setText(mTitle);
        text2.setText(mText);

        return view;
    }
}
