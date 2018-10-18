package com.example.oblong.myapplication;

import org.parceler.Parcel;

@Parcel
public class Customer {



    private String nama;
    private String alamat;
    private String keterangan;
    private long id;
    private int jumlah, total, terbayar;


    public Customer() {
    }


    public Customer(String nama, String alamat, long id, int jumlah, String keterangan) {
        this.nama = nama;
        this.alamat = alamat;
        this.id = id;
        this.jumlah = jumlah;
        this.keterangan=keterangan;
    }

    public Customer(String nama, String alamat, long id, int jumlah, String keterangan,int total, int terbayar) {
        this.nama = nama;
        this.alamat = alamat;
        this.id = id;
        this.jumlah = jumlah;
        this.total = total;
        this.terbayar = terbayar;
        this.keterangan=keterangan;
    }


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTerbayar() {
        return terbayar;
    }

    public void setTerbayar(int terbayar) {
        this.terbayar = terbayar;
    }


}
