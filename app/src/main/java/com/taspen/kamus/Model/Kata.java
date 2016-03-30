package com.taspen.kamus.Model;

/**
 * Author
 */
public class Kata {

    private String id;
    private String abrev;
    private String istilah;
    private String penjelasanBahasaIndonesia;
    private String penjelasanBahasaInggris;

    public Kata() {

    }

    public Kata(String id, String abrev, String istilah, String penjelasanBahasaIndonesia, String penjelasanBahasaInggris) {
        this.id = id;
        this.abrev = abrev;
        this.istilah = istilah;
        this.penjelasanBahasaIndonesia = penjelasanBahasaIndonesia;
        this.penjelasanBahasaInggris = penjelasanBahasaInggris;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    public String getIstilah() {
        return istilah;
    }

    public void setIstilah(String istilah) {
        this.istilah = istilah;
    }

    public String getPenjelasanBahasaIndonesia() {
        return penjelasanBahasaIndonesia;
    }

    public void setPenjelasanBahasaIndonesia(String penjelasanBahasaIndonesia) {
        this.penjelasanBahasaIndonesia = penjelasanBahasaIndonesia;
    }

    public String getPenjelasanBahasaInggris() {
        return penjelasanBahasaInggris;
    }

    public void setPenjelasanBahasaInggris(String penjelasanBahasaInggris) {
        this.penjelasanBahasaInggris = penjelasanBahasaInggris;
    }
}
