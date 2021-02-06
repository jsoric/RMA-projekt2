package com.soric.musicrma.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.soric.musicrma.model.Song;
import com.soric.musicrma.model.dao.SongDAO;
import com.soric.musicrma.model.dao.SongDB;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongViewModel extends AndroidViewModel {

    SongDAO songDAO;
    private Song song;
    private LiveData<List<Song>> songs;

    public SongViewModel(@NonNull Application application) {
        super(application);
        songDAO = SongDB.getInstance(application.getApplicationContext()).songDAO();
    }

    public LiveData<List<Song>> getAllSongs() {
        songs = songDAO.getAllSongs();
        return songs;
    }

    public void addNewSong() {
        songDAO.addNewSong(song);
    }

    public void updateSong() {
        songDAO.updateSong(song);
    }

    public void deleteSong() {
        songDAO.deleteSong(song);
    }

}
