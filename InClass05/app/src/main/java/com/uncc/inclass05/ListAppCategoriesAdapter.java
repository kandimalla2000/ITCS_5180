package com.uncc.inclass05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ListAppCategoriesAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> lstDataItem = new ArrayList<>();

    public ListAppCategoriesAdapter(Context context, ArrayList<String> objects) {
        this.context = context;
        lstDataItem = objects;
    }

    @Override
    public int getCount() {
        return lstDataItem.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.list_view_items, parent, false);

        TextView textView = (TextView) convertView.findViewById(R.id.txtContent);
        textView.setText(lstDataItem.get(position));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the user clicks on a category list item:
                //a. Replace the current fragment with the App List Fragment, pass the selected app
                //category to the App List Fragment.
                //mainActivity.setAccountGoToAppCategoriesFragment(token, account);
                //b. Push the current fragment on the back stack.

            }
        });

        return convertView;

    }

}