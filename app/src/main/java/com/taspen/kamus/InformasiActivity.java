package com.taspen.kamus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Author :)
 */
public class InformasiActivity extends Activity {

    private Button btnPengajuanKlaim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        btnPengajuanKlaim = (Button) findViewById(R.id.btn_syarat_klaim);

        btnPengajuanKlaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(InformasiActivity.this, SyaratPengajuanClaimActivity.class);
                startActivity(in);
            }
        });

    }

}
