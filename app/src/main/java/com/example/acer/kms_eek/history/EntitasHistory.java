package com.example.acer.kms_eek.history;

import com.google.gson.annotations.SerializedName;

public class EntitasHistory {
    @SerializedName("id")
    private String id;

    @SerializedName("judul_history")
    private String judul_history;

    @SerializedName("konten")
    private String konten;

    @SerializedName("id_kategori")
    private String id_kategori;

    @SerializedName("gambar_path")
    private String gambar_path;

    @SerializedName("tanggal")
    private String tanggal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul_history() {
        return judul_history;
    }

    public void setJudul_history(String judul_b) {
        this.judul_history = judul_history;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getGambar_path() {
        return gambar_path;
    }

    public void setGambar_path(String gambar_path) {
        this.gambar_path = gambar_path;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
