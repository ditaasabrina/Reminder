package com.amikom.two.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Jadwal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "hari")
    private String hari;

    @ColumnInfo(name = "jam")
    private String jam;

    @ColumnInfo(name = "ruangan")
    private String ruangan;

    @ColumnInfo(name = "makul")
    private String mataKuliah;

    @ColumnInfo(name = "dosen")
    private String dosen;

    public Jadwal(String hari, String jam, String ruangan, String mataKuliah, String dosen) {
        this.hari = hari;
        this.jam = jam;
        this.ruangan = ruangan;
        this.mataKuliah = mataKuliah;
        this.dosen = dosen;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }
}
