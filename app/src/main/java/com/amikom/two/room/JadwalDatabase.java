package com.amikom.two.room;

import android.content.Context;

import com.amikom.two.model.Jadwal;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Jadwal.class}, version = 1, exportSchema = false)
public abstract class JadwalDatabase extends RoomDatabase {
    public abstract JadwalRoom jadwalRoom();
    public static JadwalDatabase db(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                JadwalDatabase.class, "jadwal")
                .allowMainThreadQueries()
                .build();
    }

}
