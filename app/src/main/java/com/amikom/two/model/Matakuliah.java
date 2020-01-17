package com.amikom.two.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Matakuliah {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "namamatkul")
    private String namamatkul;

    @ColumnInfo(name = "dosenpengajar")
    private String dosenpengajar;

    @ColumnInfo(name = "sks")
    private String sks;

    public Matakuliah(String namamatkul, String dosenpengajar, String sks) {
        this.namamatkul = namamatkul;
        this.dosenpengajar = dosenpengajar;
        this.sks = sks;

    }

    public String getNamamatkul() {
        return namamatkul;
    }

    public void setNamamatkul(String namamatkul) {
        this.namamatkul = namamatkul;
    }

    public String getDosenpengajar() { return dosenpengajar; }

    public void setDosenpengajar(String dosenpengajar) {
        this.dosenpengajar = dosenpengajar;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }


}
