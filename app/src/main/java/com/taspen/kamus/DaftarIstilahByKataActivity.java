package com.taspen.kamus;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.taspen.kamus.Model.Kata;
import com.taspen.kamus.customAdapter.CustomIstilahAdapter;
import com.taspen.kamus.dao.KataDao;
import com.taspen.kamus.daoImpl.KataDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author :)
 */
public class DaftarIstilahByKataActivity extends Activity {

    private String istilahForSearch;
    private EditText editTextIstilah;
    private ImageView imageClearText;
    private ListView listView;
    private CustomIstilahAdapter customIstilahAdapter;

    private KataDao kataDao;

    private View loadMore;

    private int start = 0;
    private int limit = 5;

    private List<Map<String, String>> listMapIstilah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata_by_istilah);
        editTextIstilah = (EditText) findViewById(R.id.editTextIstilah);
        imageClearText = (ImageView) findViewById(R.id.image_clear_text_istilah);
        imageClearText.setVisibility(View.INVISIBLE);

        listView = (ListView) findViewById(R.id.list_istilah);



        listMapIstilah = new ArrayList<>();

        editTextIstilah.addTextChangedListener(new SearchWatcher());

        kataDao = new KataDaoImpl(DaftarIstilahByKataActivity.this);

        loadMore = ((LayoutInflater)this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.load_more, null, false);
        listView.addFooterView(loadMore);
        loadMore.setVisibility(View.INVISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(DaftarIstilahByKataActivity.this, KataDetailVersionOneActivity.class);
                Map<String, String> map = listMapIstilah.get(position);
                String idIstilah = map.get("id");
                in.putExtra("id", idIstilah);
                startActivityForResult(in, 100);
            }
        });

        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        imageClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextIstilah.setText("");
                loadAllData();
            }
        });

        //load all data istilah
        loadAllData();
    }

    private void loadData(){
        istilahForSearch = editTextIstilah.getText().toString();
        try{
            List<Kata> listKata = kataDao.findAll(istilahForSearch, start, limit);
            for(Kata k:listKata){
                start++;
                if(k.getIstilah().trim().equals("")){
                    continue;
                }
                Map<String, String> map = new HashMap<>();
                map.put("id", k.getId());
                map.put("istilah", k.getIstilah());
                listMapIstilah.add(map);
            }

            customIstilahAdapter = new CustomIstilahAdapter(DaftarIstilahByKataActivity.this, listMapIstilah);
            listView.setAdapter(customIstilahAdapter);

            if(!editTextIstilah.getText().toString().trim().equals("")) {
                imageClearText.setVisibility(View.VISIBLE);
                loadMore.setVisibility(View.VISIBLE);
            }else{
                imageClearText.setVisibility(View.INVISIBLE);
                loadMore.setVisibility(View.INVISIBLE);
//                listMapIstilah.clear();
//                customIstilahAdapter.clear();
//                customIstilahAdapter.notifyDataSetChanged();
                loadAllData();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void loadAllData(){
        try{
            List<Kata> listKata = kataDao.findAll();
            for(Kata k:listKata){
                if(k.getIstilah().trim().equals("")){
                    continue;
                }
                Map<String, String> map = new HashMap<>();
                map.put("id", k.getId());
                map.put("istilah", k.getIstilah());
                listMapIstilah.add(map);
            }

            customIstilahAdapter = new CustomIstilahAdapter(DaftarIstilahByKataActivity.this, listMapIstilah);
            listView.setAdapter(customIstilahAdapter);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private class SearchWatcher implements TextWatcher{
        private Handler handler = new Handler();
        private Runnable delayAction = null;
        private static final long DELAY_ACTION = 1000;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(delayAction != null){
                handler.removeCallbacks(delayAction);
            }

            delayAction = new Runnable() {
                @Override
                public void run() {
                    listMapIstilah.clear();
                    start = 0;
                    loadData();
                }
            };

            handler.postDelayed(delayAction, DELAY_ACTION);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        kataDao.close();
    }
}
