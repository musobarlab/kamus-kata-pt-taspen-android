package com.taspen.kamus;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Author :) PT TASPEN PERSERO
 *
 */
public class AboutActivity extends Activity {

    private TextView txtWebsiteTaspen;
    private ImageButton btnFb, btnTwitter, btnIg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        txtWebsiteTaspen = (TextView) findViewById(R.id.textWebsiteTaspen);

        btnFb = (ImageButton) findViewById(R.id.btn_fb);
        btnTwitter = (ImageButton) findViewById(R.id.btn_twitter);
        btnIg = (ImageButton) findViewById(R.id.btn_ig);

        btnFb.setBackgroundDrawable(null);
        btnTwitter.setBackgroundDrawable(null);
        btnIg.setBackgroundDrawable(null);

        txtWebsiteTaspen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taspen.com/"));
                startActivity(in);
            }
        });

        //Alamat facebook
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taspen.com/"));
                startActivity(in);
            }
        });

        //Alamat Twitter
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taspen.com/"));
                startActivity(in);
            }
        });

        //Alamat Instagram
        btnIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.taspen.com/"));
                startActivity(in);
            }
        });
    }
}
