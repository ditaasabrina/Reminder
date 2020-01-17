package com.amikom.two.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dosen {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "namadosen")
    private String namadosen;

    @ColumnInfo(name = "nik")
    private String nik;

    @ColumnInfo(name = "matkuldiampu")
    private String matkuldiampu;

    @ColumnInfo(name = "ruangan")
    private String ruangan;

    public Dosen(String namadosen, String nik, String matkuldiampu, String ruangan) {
        this.namadosen = namadosen;
        this.nik = nik;
        this.matkuldiampu = matkuldiampu;
        this.ruangan = ruangan;
    }

    public String getNamadosen() {
        return namadosen;
    }

    public void setNamadosen(String namadosen) {
        this.namadosen = namadosen;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik= nik;
    }

    public String getMatkuldiampu() {
        return matkuldiampu;
    }

    public void setMatkuldiampu(String matkuldiampu) {
        this.matkuldiampu = matkuldiampu;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    }
