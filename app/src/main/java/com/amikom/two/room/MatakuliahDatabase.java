package com.amikom.two.room;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amikom.two.model.Matakuliah;

@Database(entities = {Matakuliah.class}, version = 1, exportSchema = false)
public abstract  class MatakuliahDatabase extends RoomDatabase{
        public abstract MatakuliahRoom matakuliahRoom();
        public static MatakuliahDatabase db(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                MatakuliahDatabase.class, "matakuliah")
                .allowMainThreadQueries()
                .build();
    }
}
