package com.taspen.kamus.dao;

import com.taspen.kamus.Model.Abjad;

import java.util.List;

/**
 * Author :)
 */
public interface AbjadDao {

    List<Abjad> findAll();
    void close();
}
