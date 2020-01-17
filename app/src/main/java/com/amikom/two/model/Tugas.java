package com.amikom.two.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tugas {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "matakuliah")
    public String matakuliah;

    @ColumnInfo(name = "jenistugas")
    private String jenistugas;

    @ColumnInfo(name = "keterangan")
    private String keterangan;

    @ColumnInfo(name = "tanggalpengumpulan")
    private String tanggalpengumpulan;

    @ColumnInfo(name = "jampengumpulan")
    private String jampengumpulan;

    public Tugas(String matakuliah, String jenistugas, String keterangan, String tanggalpengumpulan, String jampengumpulan) {
        this.matakuliah = matakuliah;
        this.jenistugas = jenistugas;
        this.keterangan = keterangan;
        this.tanggalpengumpulan = tanggalpengumpulan;
        this.jampengumpulan = jampengumpulan;

    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }

    public String getJenistugas() { return jenistugas;}

    public void setJenistugas(String jenistugas) {
        this.jenistugas = jenistugas;
    }

    public String getKeterangan() { return keterangan;}

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggalpengumpulan() { return tanggalpengumpulan;}

    public void setTanggalpengumpulan(String tanggalpengumpulan) { this.tanggalpengumpulan = tanggalpengumpulan;}

    public String getJampengumpulan() { return jampengumpulan;}

    public void setJampengumpulan(String jampengumpulan) {
        this.jampengumpulan = jampengumpulan;
    }
}
