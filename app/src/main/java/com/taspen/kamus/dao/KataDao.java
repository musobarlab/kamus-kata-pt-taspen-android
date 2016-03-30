package com.taspen.kamus.dao;

import com.taspen.kamus.Model.Kata;

import java.util.List;

/**
 * Author
 */
public interface KataDao {

    Kata findOne(String id);
    List<Kata> findAll();
    List<Kata> findAll(String kata, Integer start, Integer limit);
    void close();
}
