package com.amikom.two.room;
import com.amikom.two.model.Jadwal;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface JadwalRoom {

    @Query("SELECT * FROM jadwal WHERE id = :id")
    Jadwal select(int id);

    @Query("SELECT * FROM jadwal")
    List<Jadwal> selectAll();

    @Insert
    void insert(Jadwal jadwal);

    @Update
    void update(Jadwal jadwal);

    @Delete
    void delete(Jadwal jadwal);
}
