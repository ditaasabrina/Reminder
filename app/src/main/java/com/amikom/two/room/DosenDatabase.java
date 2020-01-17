package com.amikom.two.room;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amikom.two.model.Dosen;

    @Database(entities = {Dosen.class}, version = 1, exportSchema = false)
    public abstract class DosenDatabase extends RoomDatabase {
        public abstract DosenRoom dosenRoom ();
        public static DosenDatabase db(Context context) {
            return Room.databaseBuilder(
                    context.getApplicationContext(),
                    DosenDatabase.class, "dosen")
                    .allowMainThreadQueries()
                    .build();
        }


    }

