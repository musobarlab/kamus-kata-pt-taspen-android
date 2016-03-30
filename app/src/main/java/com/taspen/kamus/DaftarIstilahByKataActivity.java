package com.taspen.kamus;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.taspen.kamus.Model.Kata;
import com.taspen.kamus.dao.KataDao;
import com.taspen.kamus.daoImpl.KataDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author :)
 */
public class DaftarIstilahByKataActivity extends ListActivity {

    private String istilahForSearch;
    private EditText editTextIstilah;
    private ListView listView;

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
        listView = getListView();

        listMapIstilah = new ArrayList<>();
        editTextIstilah.addTextChangedListener(new SearchWatcher());

        kataDao = new KataDaoImpl(DaftarIstilahByKataActivity.this);

        loadMore = ((LayoutInflater)this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.load_more, null, false);
        listView.addFooterView(loadMore);

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
    }

    private void loadData(){
        istilahForSearch = editTextIstilah.getText().toString();
        try{
            List<Kata> listKata = kataDao.findAll(istilahForSearch, start, limit);
            for(Kata k:listKata){
                start++;
                Map<String, String> map = new HashMap<>();
                map.put("id", k.getId());
                map.put("istilah", k.getIstilah());
                listMapIstilah.add(map);
            }
            ListAdapter listAdapter = new SimpleAdapter(DaftarIstilahByKataActivity.this, listMapIstilah,R.layout.list_kata_by_istilah_single,new String[]{"istilah"}, new int[]{R.id.txt_istilah_on_activity_kata_by_istilah});
            listView.setAdapter(listAdapter);
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
