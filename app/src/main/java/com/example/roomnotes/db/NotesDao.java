package com.example.roomnotes.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void addNote(Notes... notes);

    @Query("SELECT * FROM Notes")
    List<Notes> getAllNotes();

    @Delete
    void delNotes(Notes notes);

    @Update
    void updateNote(Notes notes);
}
