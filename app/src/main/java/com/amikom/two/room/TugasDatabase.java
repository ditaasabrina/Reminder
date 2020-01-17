package com.amikom.two.room;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amikom.two.model.Tugas;

@Database(entities = {Tugas.class}, version = 1, exportSchema = false)
public abstract class TugasDatabase extends RoomDatabase {
    public abstract TugasRoom tugasRoom();
    public static TugasDatabase db(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                TugasDatabase.class, "tugas")
                .allowMainThreadQueries()
                .build();
    }
}
