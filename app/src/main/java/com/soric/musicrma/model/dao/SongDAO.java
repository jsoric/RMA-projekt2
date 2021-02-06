package com.soric.musicrma.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.soric.musicrma.model.Song;

import java.util.List;

@Dao
public interface SongDAO {

    @Query("SELECT * FROM song order by created DESC")
    LiveData<List<Song>> getAllSongs();

    @Insert
    void addNewSong(Song song);

    @Update
    void updateSong(Song song);

    @Delete
    void deleteSong(Song song);

}
