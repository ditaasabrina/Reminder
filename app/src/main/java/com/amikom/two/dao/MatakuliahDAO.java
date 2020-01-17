package com.amikom.two.dao;
import com.amikom.two.model.Matakuliah;
import java.util.ArrayList;
import java.util.List;

public class MatakuliahDAO {
    private List<Matakuliah> list = new ArrayList<>();

    public MatakuliahDAO() {
        list.add(new Matakuliah ("","",""));
    }

    public void insert(Matakuliah matakuliah) {
        list.add(matakuliah);
    }

    public void update(int id, Matakuliah matakuliah) {
        Matakuliah old = list.get(id);
        old.setNamamatkul(matakuliah.getNamamatkul());
        old.setDosenpengajar(matakuliah.getDosenpengajar());
        old.setSks(matakuliah.getSks());
    }

    public void delete(int id) {
        list.remove(id);
    }

    public Matakuliah select(int id) {
        return list.get(id);
    }

    public List<Matakuliah> selectAll() {
        return list;
    }
}
