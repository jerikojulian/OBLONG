package com.example.oblong.myapplication;


import org.parceler.Parcel;

@Parcel
public class ItemOrder {

    private long id_customer;
    private String jenis, ukuran;

    public ItemOrder(){

    }

    public ItemOrder(long id_customer, String jenis, int harga, String ukuran) {
        this.id_customer = id_customer;
        this.jenis = jenis;
        this.harga = harga;
        this.ukuran=ukuran;
    }


    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public long getId_customer() {
        return id_customer;
    }

    public void setId_customer(long id_customer) {
        this.id_customer = id_customer;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    private int harga;
}
