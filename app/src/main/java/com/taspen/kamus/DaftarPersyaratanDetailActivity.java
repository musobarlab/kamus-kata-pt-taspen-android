package com.taspen.kamus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.taspen.kamus.Model.Persyaratan;
import com.taspen.kamus.Model.PersyaratanDetail;
import com.taspen.kamus.dao.PersyaratanDao;
import com.taspen.kamus.dao.PersyaratanDetailDao;
import com.taspen.kamus.daoImpl.PersyaratanDaoImpl;
import com.taspen.kamus.daoImpl.PersyaratanDetailDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author :)
 */
public class DaftarPersyaratanDetailActivity extends ListActivity {

    private PersyaratanDetailDao persyaratanDetailDao;
    private PersyaratanDao persyaratanDao;

    private ListView listView;
    private TextView textViewTitle;

    private List<Map<String, String>> listPersyaratanDetail;
    private String idPersyaratan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_persyaratan_klaim_detail_activity);
        persyaratanDetailDao = new PersyaratanDetailDaoImpl(DaftarPersyaratanDetailActivity.this);
        persyaratanDao = new PersyaratanDaoImpl(DaftarPersyaratanDetailActivity.this);
        listView = getListView();
        listPersyaratanDetail = new ArrayList<>();

        Intent intent = getIntent();
        idPersyaratan = intent.getStringExtra("id_syarat");

        textViewTitle = (TextView) findViewById(R.id.text_title_persyaratan_on_detail);
        loadSyaratDetail();

    }

    private void loadSyaratDetail(){
        try{
            List<PersyaratanDetail> list = persyaratanDetailDao.findByPersyaratan(idPersyaratan);
            for(PersyaratanDetail p:list){
                Map<String, String> map = new HashMap<>();
                map.put("id", p.getId());
                map.put("id_persyaratan", p.getIdPersyaratan());
                map.put("isi", p.getIsi());
                listPersyaratanDetail.add(map);
            }

            String idPersyaratan = listPersyaratanDetail.get(0).get("id_persyaratan");
            Persyaratan persyaratan = persyaratanDao.findOne(idPersyaratan);
            textViewTitle.setText(persyaratan.getIsi());
            ListAdapter listAdapter = new SimpleAdapter(DaftarPersyaratanDetailActivity.this, listPersyaratanDetail, R.layout.list_syarat_klaim_detail_single,new String[]{"isi"}, new int[]{R.id.txt_syarat_klaim_detail});
            setListAdapter(listAdapter);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persyaratanDetailDao.close();
    }
}
