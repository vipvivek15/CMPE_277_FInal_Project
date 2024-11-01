package com.example.cmpe_277_mobile_final_project;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)  // Assuming you have a DateConverter for handling Date fields
public abstract class TaskDatabase extends RoomDatabase {

    // Singleton instance
    private static volatile TaskDatabase INSTANCE;

    // Link to TaskDao
    public abstract TaskDao taskDao();

    // Get the singleton instance of the database
    public static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "task_database")
                            .fallbackToDestructiveMigration()  // Optional: for easy migration in development
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
