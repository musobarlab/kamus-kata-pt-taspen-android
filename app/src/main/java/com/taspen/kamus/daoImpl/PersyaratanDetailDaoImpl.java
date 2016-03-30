package com.taspen.kamus.daoImpl;

import android.content.Context;
import android.database.Cursor;

import com.taspen.kamus.Model.PersyaratanDetail;
import com.taspen.kamus.dao.PersyaratanDetailDao;
import com.taspen.kamus.helper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author :)
 */
public class PersyaratanDetailDaoImpl implements PersyaratanDetailDao {

    private DatabaseHelper databaseHelper;

    public PersyaratanDetailDaoImpl(Context ctx){
        databaseHelper = new DatabaseHelper(ctx);
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseHelper.openDataBase();
    }

    @Override
    public List<PersyaratanDetail> findByPersyaratan(String idPersyaratan) {
        List<PersyaratanDetail> list = new ArrayList<>();
        String [] selections = new String[1];
        selections[0] = idPersyaratan;
        Cursor c = databaseHelper.getDatabase().rawQuery("SELECT * FROM persyaratan_detail WHERE id_persyaratan = ?", selections);
        c.moveToFirst();
        while(!c.isAfterLast()){
            PersyaratanDetail p = persyaratanDetailToCursor(c);
            list.add(p);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    private PersyaratanDetail persyaratanDetailToCursor(Cursor c){
        PersyaratanDetail p = new PersyaratanDetail();
        p.setId(c.getString(0));
        p.setIdPersyaratan(c.getString(1));
        p.setIsi(c.getString(2));
        return p;
    }

    @Override
    public void close() {
        databaseHelper.close();
    }
}
