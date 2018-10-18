package com.example.oblong.myapplication;

import org.parceler.Parcel;

/**
 * Created by Jupe Taek on 10/6/2017.
 */

@Parcel
public class Order {

    private String namaPemesan, jenis, deskripsi,deadline;
    private int harga, jumlah, belanjaBahan, potong, salonBordir, jahit, pengeluaranLain, keuntungan;
    private String key;
    private boolean isInputBiaya;
    private boolean isDone;



    public Order() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Order(String namaPemesan, String jenis, String deskripsi,int jumlah) {
        this.namaPemesan=namaPemesan;
        this.jenis=jenis;
        this.deskripsi=deskripsi;
        this.jumlah=jumlah;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setKeuntungan(int keuntungan) {
        this.keuntungan = keuntungan;
    }

    public void setIsInputBiaya(boolean inputBiaya) {
        isInputBiaya = inputBiaya;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public void setnamaPemesan(String namaPemesan){
        this.namaPemesan=namaPemesan;
    }

    public void setJenis(String jenis){
        this.jenis=jenis;
    }

    public void setJumlah(int jumlah){
        this.jumlah=jumlah;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi=deskripsi;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setBelanjaBahan(int belanjaBahan) {
        this.belanjaBahan = belanjaBahan;
    }

    public void setPotong(int potong) {
        this.potong = potong;
    }

    public void setJahit(int jahit) {
        this.jahit = jahit;
    }

    public void setPengeluaranLain(int pengeluaranLain) {
        this.pengeluaranLain = pengeluaranLain;
    }

    public void setSalonBordir(int salonBordir) {
        this.salonBordir = salonBordir;
    }

    public int getKeuntungan() {
        return keuntungan;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean getIsInputBiaya() {
        return isInputBiaya;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public String getJenis() {
        return jenis;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getBelanjaBahan() {
        return belanjaBahan;
    }

    public int getHarga() {
        return harga;
    }

    public int getJahit() {
        return jahit;
    }

    public int getPengeluaranLain() {
        return pengeluaranLain;
    }

    public int getPotong() {
        return potong;
    }

    public int getSalonBordir() {
        return salonBordir;
    }

    public String getKey() {
        return key;
    }


}
