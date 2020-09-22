package com.example.project2.repository.db;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.project2.repository.db.dao.RasporedDao;
import com.example.project2.repository.db.entity.RasporedEntity;

@Database(entities = {RasporedEntity.class}, version = 6, exportSchema = false)
public abstract class RasporedDatabase extends RoomDatabase {

    private static volatile RasporedDatabase DATABASE;

    private static final String DATABASE_NAME = "raspored.db";

    public abstract RasporedDao getRasporedDao();

    public static RasporedDatabase getDb(Context context) {
        if (DATABASE == null) {
            synchronized (RasporedDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = Room.databaseBuilder(context, RasporedDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            //.fallbackToDestructiveMigrationOnDowngrade()
                            .build();
                }
            }
        }

        return DATABASE;
    }
}
