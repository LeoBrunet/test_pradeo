package com.example.test_pradeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
    public ApplicationAdapter(Context context, ArrayList<ApplicationInfo> apps) {
        super(context, 0, apps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ApplicationInfo app = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_application, parent, false);
        }
        // Lookup view for data population
        ImageView icon = convertView.findViewById(R.id.icon);
        TextView name = convertView.findViewById(R.id.name);
        TextView hash = convertView.findViewById(R.id.hash);
        // Populate the data into the template view using the data object
        icon.setImageDrawable(app.getIcon());
        name.setText(app.getName());
        hash.setText(app.getHash());
        // Return the completed view to render on screen
        return convertView;
    }
}