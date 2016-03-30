package com.taspen.kamus.daoImpl;

import android.content.Context;
import android.database.Cursor;

import com.taspen.kamus.Model.Abjad;
import com.taspen.kamus.dao.AbjadDao;
import com.taspen.kamus.helper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author
 */
public class AbjadDaoImpl implements AbjadDao {

    private DatabaseHelper databaseHelper;
    private static final String[] KOLOM = {"id", "huruf"};

    public AbjadDaoImpl(Context context){
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseHelper.openDataBase();

    }


    @Override
    public List<Abjad> findAll() {
        List<Abjad> list = new ArrayList<>();
        Cursor c = databaseHelper.getDatabase().query("abjad", KOLOM, null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Abjad a = abjadToCursor(c);
            list.add(a);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    @Override
    public void close() {
        databaseHelper.close();
    }

    private Abjad abjadToCursor(Cursor c){
        Abjad a = new Abjad();
        a.setId(c.getInt(0));
        a.setHuruf(c.getString(1));
        return a;
    }
}
