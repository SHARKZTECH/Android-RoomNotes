package com.example.roomnotes.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

@Database(entities = Notes.class,version = 2)
public abstract class AppDb extends RoomDatabase {
    public abstract NotesDao notesDao();

    private static AppDb INSTANCE;

    public static AppDb getINSTANCE(Context context) {
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDb.class,"NOTES")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
