package com.soric.musicrma.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soric.musicrma.R;
import com.soric.musicrma.model.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.Row> {

    private List<Song> data;
    private SongClickListener songClickListener;

    public SongAdapter(SongClickListener songClickListener) {
        this.songClickListener = songClickListener;
    }

    @NonNull
    @Override
    public Row onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_row, parent, false);
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Row holder, int position) {
        Song song = data.get(position);
        switch (song.getGenre()) {
            case 0:
                Picasso.get().load(R.drawable.pop).into(holder.image);
                break;
            case 1:
                Picasso.get().load(R.drawable.electronic).into(holder.image);
                break;
            case 2:
                Picasso.get().load(R.drawable.rock).into(holder.image);
                break;
            case 3:
                Picasso.get().load(R.drawable.soul).into(holder.image);
                break;
            case 4:
                Picasso.get().load(R.drawable.reggae).into(holder.image);
                break;
            case 5:
                Picasso.get().load(R.drawable.punk).into(holder.image);
                break;
            case 6:
                Picasso.get().load(R.drawable.latin).into(holder.image);
                break;
            case 7:
                Picasso.get().load(R.drawable.k_pop).into(holder.image);
                break;
            case 8:
                Picasso.get().load(R.drawable.hip_hop).into(holder.image);
                break;
            case 9:
                Picasso.get().load(R.drawable.country).into(holder.image);
                break;
        }
        holder.title.setText(song.getTitle());
        holder.performer.setText(song.getPerformer());
        holder.album.setText(song.getAlbum());
        holder.yearOfIssue.setText(String.valueOf(song.getYear_of_issue()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songClickListener.onItemClick(song);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<Song> songs) {
        this.data = songs;
    }

    public class Row extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView performer;
        private TextView album;
        private TextView yearOfIssue;
        public Row(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            performer = itemView.findViewById(R.id.performer);
            album = itemView.findViewById(R.id.album);
            yearOfIssue = itemView.findViewById(R.id.year_of_issue);
        }
    }

}
