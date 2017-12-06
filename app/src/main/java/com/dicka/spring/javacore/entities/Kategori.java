package com.dicka.spring.javacore.entities;

import java.io.Serializable;

/**
 * Created by java-spring on 06/12/17.
 */

public class Kategori implements Serializable{

    private int idkategori;
    private String nama;
    private String deskripsi;

    public Kategori(){

    }

    public Kategori(int idkategori, String nama, String deskripsi){
        super();
        this.idkategori = idkategori;
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    public int getIdkategori(){
        return idkategori;
    }

    public void setIdkategori(int idkategori){
        this.idkategori = idkategori;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
}
