package com.taspen.kamus.customAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.taspen.kamus.R;

import java.util.List;
import java.util.Map;

/**
 * Author :)
 */
public class CustomIstilahAdapter extends ArrayAdapter<Map<String, String>> {

    public CustomIstilahAdapter(Activity activity, List<Map<String, String>> maps){
        super(activity.getApplicationContext(), 0, maps);

    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Map<String, String> map = getItem(position);
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_kata_by_istilah_single, parent, false);
        }

        TextView textViewIstilah = (TextView) v.findViewById(R.id.txt_istilah_on_activity_kata_by_istilah);
        textViewIstilah.setText(map.get("istilah"));
        return v;
    }
}
