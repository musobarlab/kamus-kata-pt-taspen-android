package com.taspen.kamus.Model;

/**
 * Author :)
 */
public class PersyaratanDetail {

    private String id;
    private String idPersyaratan;
    private String isi;

    public PersyaratanDetail(){}

    public PersyaratanDetail(String id, String idPersyaratan, String isi) {
        this.id = id;
        this.idPersyaratan = idPersyaratan;
        this.isi = isi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPersyaratan() {
        return idPersyaratan;
    }

    public void setIdPersyaratan(String idPersyaratan) {
        this.idPersyaratan = idPersyaratan;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
