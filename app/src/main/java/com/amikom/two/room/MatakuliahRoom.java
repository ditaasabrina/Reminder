package com.amikom.two.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.amikom.two.model.Matakuliah;
import java.util.List;

@Dao
public interface MatakuliahRoom {

        @Query("SELECT * FROM matakuliah WHERE id = :id")
        Matakuliah select(int id);
        @Query("SELECT * FROM matakuliah")
        List<Matakuliah> selectAll();

        @Insert
         void insert(Matakuliah matakuliah);

        @Update
        void update(Matakuliah matakuliah);

        @Delete
        void delete(Matakuliah matakuliah);
    }

