package com.taspen.kamus.dao;

import com.taspen.kamus.Model.Persyaratan;

import java.util.List;

/**
 * Author
 */
public interface PersyaratanDao {

    List<Persyaratan> findAll();
    Persyaratan findOne(String id);
    void close();
}
