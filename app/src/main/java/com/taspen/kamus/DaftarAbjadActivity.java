package com.taspen.kamus;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.taspen.kamus.Model.Abjad;
import com.taspen.kamus.customAdapter.CustomListAbjad;
import com.taspen.kamus.dao.AbjadDao;
import com.taspen.kamus.daoImpl.AbjadDaoImpl;

import java.util.List;

/**
 * Author :)
 */
public class DaftarAbjadActivity extends ListActivity {

    private AbjadDao abjadDao;
    private List<Abjad> listAbjad;
    private String [] abjadArray;
    private ListView listView;

    private final Integer[] imageId = {R.drawable.letter_a,
            R.drawable.letter_b,
            R.drawable.letter_c,
            R.drawable.letter_d,
            R.drawable.letter_e,
            R.drawable.letter_f,
            R.drawable.letter_g,
            R.drawable.letter_h,
            R.drawable.letter_i,
            R.drawable.letter_j,
            R.drawable.letter_k,
            R.drawable.letter_l,
            R.drawable.letter_m,
            R.drawable.letter_n,
            R.drawable.letter_o,
            R.drawable.letter_p,
            R.drawable.letter_q,
            R.drawable.letter_r,
            R.drawable.letter_s,
            R.drawable.letter_t,
            R.drawable.letter_u,
            R.drawable.letter_v,
            R.drawable.letter_w,
            R.drawable.letter_x,
            R.drawable.letter_y,
            R.drawable.letter_z,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_abjad);

        abjadDao = new AbjadDaoImpl(DaftarAbjadActivity.this);
        listView = getListView();

        loadData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void loadData(){
        listAbjad = abjadDao.findAll();
        abjadArray = new String[listAbjad.size()];
        for(int i=0;i<listAbjad.size();i++){
            Abjad a = listAbjad.get(i);
            abjadArray[i] = a.getHuruf();
        }

        CustomListAbjad customListAbjad = new CustomListAbjad(DaftarAbjadActivity.this, abjadArray, imageId);
        listView.setAdapter(customListAbjad);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        abjadDao.close();
    }
}
