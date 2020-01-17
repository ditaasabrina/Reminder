package com.amikom.two.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.amikom.two.model.Dosen;
import java.util.List;

@Dao
public interface DosenRoom {

    @Query("SELECT * FROM dosen WHERE id = :id")
    Dosen select(int id);
    @Query("SELECT * FROM dosen")
    List<Dosen> selectAll();

    @Insert
    void insert(Dosen dosen);

    @Update
    void update(Dosen dosen);

    @Delete
    void delete(Dosen dosen);
}
