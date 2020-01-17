package com.amikom.two.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.amikom.two.model.Tugas;
import java.util.List;

@Dao
    public interface TugasRoom {

        @Query("SELECT * FROM tugas WHERE id = :id")
        Tugas select(int id);
        @Query("SELECT * FROM tugas")
         List<Tugas> selectAll();

        @Insert
        void insert(Tugas tugas);

        @Update
        void update(Tugas tugas);

        @Delete
        void delete(Tugas tugas);
    }

