package com.taspen.kamus.Model;

/**
 * Author :)
 */
public class Persyaratan {

    private String id;
    private String isi;

    public Persyaratan(){}

    public Persyaratan(String id, String isi) {
        this.id = id;
        this.isi = isi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
