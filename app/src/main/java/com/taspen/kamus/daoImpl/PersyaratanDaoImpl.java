package com.taspen.kamus.daoImpl;

import android.content.Context;
import android.database.Cursor;

import com.taspen.kamus.Model.Persyaratan;
import com.taspen.kamus.dao.PersyaratanDao;
import com.taspen.kamus.helper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author
 */
public class PersyaratanDaoImpl implements PersyaratanDao {

    private DatabaseHelper databaseHelper;
    private static final String [] KOLOM = {"id", "isi"};

    public PersyaratanDaoImpl(Context ctx){
        databaseHelper = new DatabaseHelper(ctx);
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseHelper.openDataBase();
    }

    @Override
    public List<Persyaratan> findAll() {
        List<Persyaratan> list = new ArrayList<>();
        Cursor c = databaseHelper.getDatabase().query("persyaratan", KOLOM, null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Persyaratan p = cursorToPersyaratan(c);
            list.add(p);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    @Override
    public Persyaratan findOne(String id) {
        Persyaratan p = new Persyaratan();
        Cursor c = databaseHelper.getDatabase().query(true, "persyaratan", KOLOM, "id='"+id+"'",null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
            p.setId(c.getString(0));
            p.setIsi(c.getString(1));
        }
        c.close();
        return p;
    }

    @Override
    public void close() {
        databaseHelper.close();
    }

    private Persyaratan cursorToPersyaratan(Cursor c){
        Persyaratan p = new Persyaratan();
        p.setId(c.getString(0));
        p.setIsi(c.getString(1));
        return p;
    }
}
