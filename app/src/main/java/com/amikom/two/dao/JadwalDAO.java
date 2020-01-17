package com.amikom.two.dao;

import com.amikom.two.model.Jadwal;

import java.util.ArrayList;
import java.util.List;

public class JadwalDAO {
    private List<Jadwal> list = new ArrayList<>();

    public JadwalDAO() {
        list.add(new Jadwal("","", "","", ""));

    }

    public void insert(Jadwal jadwal) {
        list.add(jadwal);
    }

    public void update(int id, Jadwal jadwal) {
        Jadwal old = list.get(id);
        old.setHari(jadwal.getHari());
        old.setJam(jadwal.getJam());
        old.setRuangan(jadwal.getRuangan());
        old.setMataKuliah(jadwal.getMataKuliah());
        old.setDosen(jadwal.getDosen());
    }

    public void delete(int id) {
        list.remove(id);
    }

    public Jadwal select(int id) {
        return list.get(id);
    }

    public List<Jadwal> selectAll() {
        return list;
    }
}
