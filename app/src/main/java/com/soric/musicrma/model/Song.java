package com.soric.musicrma.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "song")
public class Song {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private int genre;
    private String title;
    private String performer;
    private String album;
    private int year_of_issue;
    private long created;
    private long updated;

}
