package com.soric.musicrma.model.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.soric.musicrma.model.Song;

@Database(entities = {Song.class}, version = 1, exportSchema = false)
public abstract class SongDB extends RoomDatabase {

    public abstract SongDAO songDAO();

    private static SongDB instance;

    public static SongDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    SongDB.class,
                    "song-db"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

}
