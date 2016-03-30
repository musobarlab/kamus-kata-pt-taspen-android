package com.taspen.kamus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.taspen.kamus.Model.Persyaratan;
import com.taspen.kamus.dao.PersyaratanDao;
import com.taspen.kamus.daoImpl.PersyaratanDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author :)
 */
public class SyaratPengajuanClaimActivity extends ListActivity {

    private ListView listView;

    private List<Map<String, String>> listPersyaratan;
    private PersyaratanDao persyaratanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syarat_pengajuan_klaim_activity);
        persyaratanDao = new PersyaratanDaoImpl(SyaratPengajuanClaimActivity.this);
        listView = getListView();
        listPersyaratan = new ArrayList<>();

        loadDataPersyaratan();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> mapSyarat = listPersyaratan.get(position);
                String idSyarat = mapSyarat.get("id");
                Intent in = new Intent(SyaratPengajuanClaimActivity.this, DaftarPersyaratanDetailActivity.class);
                in.putExtra("id_syarat", idSyarat);
                startActivityForResult(in, 100);

            }
        });
    }

    private void loadDataPersyaratan(){
        try{
            List<Persyaratan> list = persyaratanDao.findAll();
            for(Persyaratan p:list){
                Map<String, String> map = new HashMap<>();
                map.put("id", p.getId());
                map.put("isi", p.getIsi());
                listPersyaratan.add(map);
            }
            ListAdapter listAdapter = new SimpleAdapter(SyaratPengajuanClaimActivity.this, listPersyaratan, R.layout.list_syarat_klaim_single, new String[]{"isi"}, new int[]{R.id.txt_syarat_klaim});
            listView.setAdapter(listAdapter);
        }catch(Exception ex){

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persyaratanDao.close();
    }
}
