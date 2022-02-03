package com.example.acer.kms_eek.kategori;

import com.google.gson.annotations.SerializedName;

public class EntitasKategori {
    @SerializedName("id")
    private String id;

    @SerializedName("judul_berita")
    private String judul_berita;

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

    public String getJudul_berita() {
        return judul_berita;
    }

    public void setJudul_berita(String judul_berita) {
        this.judul_berita = judul_berita;
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
