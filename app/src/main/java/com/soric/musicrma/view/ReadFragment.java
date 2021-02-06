package com.soric.musicrma.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soric.musicrma.R;
import com.soric.musicrma.model.Song;
import com.soric.musicrma.view.adapter.SongAdapter;
import com.soric.musicrma.view.adapter.SongClickListener;
import com.soric.musicrma.viewmodel.SongViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadFragment extends Fragment {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    SongViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.read_fragment, container, false);
        ButterKnife.bind(this, view);
        model = ((MainActivity) getActivity()).getModel();

        defineList();

        refreshData();

        return view;
    }

    public void refreshData() {
        model.getAllSongs().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                ((SongAdapter)recyclerView.getAdapter()).setData(songs);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    public void defineList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SongAdapter(new SongClickListener() {
            @Override
            public void onItemClick(Song song) {
                model.setSong(song);
                ((MainActivity)getActivity()).cud();
            }
        }));
    }

    @OnClick(R.id.fab)
    public void newSong(){
        model.setSong(new Song());
        ((MainActivity)getActivity()).cud();
    }

}
