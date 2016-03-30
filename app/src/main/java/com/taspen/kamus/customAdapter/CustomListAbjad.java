package com.taspen.kamus.customAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taspen.kamus.R;

/**
 * Author :)
 */
public class CustomListAbjad extends ArrayAdapter<String>{

    private Activity context;
    private String[] abjad;
    private Integer[] imageId;


    public CustomListAbjad(Activity context, String[] abjad, Integer[]imageId) {
        super(context, R.layout.list_abjad_single, abjad);
        this.context = context;
        this.abjad = abjad;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_abjad_single, null, true);
        TextView textView = (TextView) rowView.findViewById(R.id.txt_huruf);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_huruf);
        textView.setText("Daftar istilah abjad = ");
        imageView.setImageResource(imageId[position]);
        return rowView;
    }


}
