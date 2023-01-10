package com.example.roomnotes.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "createdAt")
    public Long createdAt;
}
