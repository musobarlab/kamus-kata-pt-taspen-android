package com.taspen.kamus.daoImpl;

import android.content.Context;
import android.database.Cursor;

import com.taspen.kamus.Model.Kata;
import com.taspen.kamus.dao.KataDao;
import com.taspen.kamus.helper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author
 */
public class KataDaoImpl implements KataDao{

    private DatabaseHelper databaseHelper;
    private static final String [] KOLOM = {"id", "abrev", "istilah", "penjelasan_bahasa_indonesia", "penjelasan_bahasa_inggris"};

    public KataDaoImpl(Context ctx){
        databaseHelper = new DatabaseHelper(ctx);
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseHelper.openDataBase();
    }

    @Override
    public Kata findOne(String id) {
        Kata kata = new Kata();
        Cursor c = databaseHelper.getDatabase().query(true, "kata", KOLOM, "id='"+id+"'",null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
            kata.setId(c.getString(0));
            kata.setAbrev(c.getString(1));
            kata.setIstilah(c.getString(2));
            kata.setPenjelasanBahasaIndonesia(c.getString(3));
            kata.setPenjelasanBahasaInggris(c.getString(4));
        }
        c.close();
        return kata;
    }

    @Override
    public List<Kata> findAll() {
        List<Kata> list = new ArrayList<>();
        Cursor c = databaseHelper.getDatabase().query("kata", KOLOM, null,null, null, null, "istilah ASC");
        c.moveToFirst();
        while(!c.isAfterLast()){
            Kata k = cursorToKata(c);
            list.add(k);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    @Override
    public List<Kata> findAll(String kata, Integer start, Integer limit) {
        List<Kata> list = new ArrayList<>();
        String [] selections = new String[3];
        selections[0] = "%"+kata+"%";
        selections[1] = String.valueOf(limit);
        selections[2] = String.valueOf(start);
        Cursor c = databaseHelper.getDatabase().rawQuery("SELECT * FROM kata WHERE istilah LIKE ? ORDER BY istilah ASC LIMIT ? OFFSET ?", selections);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Kata k = cursorToKata(c);
            list.add(k);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    @Override
    public void close() {
        databaseHelper.close();
    }

    private Kata cursorToKata(Cursor c){
        Kata kata = new Kata();
        kata.setId(c.getString(0));
        kata.setAbrev(c.getString(1));
        kata.setIstilah(c.getString(2));
        kata.setPenjelasanBahasaIndonesia(c.getString(3));
        kata.setPenjelasanBahasaInggris(c.getString(4));
        return kata;
    }
}
