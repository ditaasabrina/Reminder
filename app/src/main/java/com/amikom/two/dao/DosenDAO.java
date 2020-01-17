package com.amikom.two.dao;
import com.amikom.two.model.Dosen;
import java.util.ArrayList;
import java.util.List;

class DosenDAO {
    private List<Dosen> list = new ArrayList<>();

    public DosenDAO() {
        list.add(new Dosen("", "","",""));
        }

    public void insert(Dosen dosen) {list.add(dosen); }

    public void update(int id, Dosen dosen) {
        Dosen old = list.get(id);
        old.setNamadosen(dosen.getNamadosen());
        old.setNik(dosen.getNik());
        old.setMatkuldiampu(dosen.getMatkuldiampu());
        old.setRuangan(dosen.getRuangan());
    }

    public void delete(int id) {
        list.remove(id);
    }

    public Dosen dosen (int id) {
        return list.get(id);
    }

    public List<Dosen> selectAll() {
        return list;
    }
}
