package com.taspen.kamus;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Author :) PT TASPEN PERSERO
 *
 */
public class AboutActivity extends Activity {

    private TextView txtWebsiteTaspen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        txtWebsiteTaspen = (TextView) findViewById(R.id.textWebsiteTaspen);
        txtWebsiteTaspen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taspen.com/"));
                startActivity(in);
            }
        });
    }
}
