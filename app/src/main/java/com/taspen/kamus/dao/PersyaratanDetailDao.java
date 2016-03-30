package com.taspen.kamus.dao;

import com.taspen.kamus.Model.PersyaratanDetail;

import java.util.List;

/**
 * Author :)
 */
public interface PersyaratanDetailDao {

    List<PersyaratanDetail> findByPersyaratan(String idPersyaratan);
}
