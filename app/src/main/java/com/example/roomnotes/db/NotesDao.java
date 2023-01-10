package com.example.roomnotes.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void addNote(Notes... notes);

    @Query("SELECT * FROM notes")
    List<Notes> getAllNotes();

    @Delete
    void delNotes(Notes notes);
}
