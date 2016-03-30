package com.taspen.kamus.Model;

/**
 * Author
 */
public class Abjad {

    private Integer id;
    private String huruf;

    public Abjad(){}

    public Abjad(Integer id, String huruf) {
        this.id = id;
        this.huruf = huruf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHuruf() {
        return huruf;
    }

    public void setHuruf(String huruf) {
        this.huruf = huruf;
    }
}
