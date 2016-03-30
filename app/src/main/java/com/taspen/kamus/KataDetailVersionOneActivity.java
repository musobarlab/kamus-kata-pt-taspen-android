package com.taspen.kamus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.taspen.kamus.Model.Kata;
import com.taspen.kamus.dao.KataDao;
import com.taspen.kamus.daoImpl.KataDaoImpl;

/**
 * Author :)
 */
public class KataDetailVersionOneActivity extends Activity {

    private KataDao kataDao;

    private TextView txtIstilah, txtKataBahasaIndonsia, txtKataBahasaInggris;
    private String istilahId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata_detail_v_1);
        kataDao = new KataDaoImpl(KataDetailVersionOneActivity.this);

        //initial variable textView
        txtIstilah = (TextView) findViewById(R.id.txt_istilah_v_1);
        txtKataBahasaIndonsia = (TextView) findViewById(R.id.txt_penjelasan_bahasa_indonesia_v_1);
        txtKataBahasaInggris = (TextView) findViewById(R.id.txt_penjelasan_bahasa_inggris_v_1);

        Intent intent = getIntent();
        istilahId = intent.getStringExtra("id");

        loadData();
    }

    private void loadData(){
        try{
            Kata k = kataDao.findOne(istilahId);
            txtIstilah.setText(k.getIstilah());
            txtKataBahasaIndonsia.setText(k.getPenjelasanBahasaIndonesia());
            txtKataBahasaInggris.setText(k.getPenjelasanBahasaInggris());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
