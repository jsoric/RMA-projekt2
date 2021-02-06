package com.soric.musicrma.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.soric.musicrma.R;
import com.soric.musicrma.model.Song;
import com.soric.musicrma.viewmodel.SongViewModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CUDFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.genre)
    Spinner genre;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.performer)
    TextView performer;
    @BindView(R.id.album)
    TextView album;
    @BindView(R.id.year_of_issue)
    TextView yearOfIssue;
    @BindView(R.id.created)
    TextView created;
    @BindView(R.id.updated)
    TextView updated;

    @BindView(R.id.genreLayout)
    LinearLayout genreLayout;
    @BindView(R.id.titleLayout)
    LinearLayout titleLayout;
    @BindView(R.id.performerLayout)
    LinearLayout performerLayout;
    @BindView(R.id.albumLayout)
    LinearLayout albumLayout;
    @BindView(R.id.yearOfIssueLayout)
    LinearLayout yearOfIssueLayout;

    @BindView(R.id.newSong)
    Button newSong;
    @BindView(R.id.updateSong)
    Button updateSong;
    @BindView(R.id.deleteSong)
    Button deleteSong;

    SongViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cud_fragment, container, false);
        ButterKnife.bind(this, view);
        model = ((MainActivity)getActivity()).getModel();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);
        genre.setOnItemSelectedListener(this);

        genreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genre.performClick();
            }
        });
        titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertInput(getString(R.string.titleInputTitle), model.getSong().getTitle(), title, InputType.TYPE_CLASS_TEXT);
            }
        });
        performerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertInput(getString(R.string.performerInputTitle), model.getSong().getPerformer(), performer, InputType.TYPE_CLASS_TEXT);
            }
        });
        albumLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertInput(getString(R.string.albumInputTitle), model.getSong().getAlbum(), album, InputType.TYPE_CLASS_TEXT);
            }
        });
        yearOfIssueLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertInput(getString(R.string.yearOfIssueInputTitle), String.valueOf(model.getSong().getYear_of_issue()), yearOfIssue, InputType.TYPE_CLASS_NUMBER);
            }
        });

        if (model.getSong().getId() == 0) {
            defineNewSong();
            return view;
        }

        defineUpdateAndDeleteSong();
        return view;
    }

    private void defineNewSong() {
        updateSong.setVisibility(View.GONE);
        deleteSong.setVisibility(View.GONE);
        newSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSong();
            }
        });
    }

    private void newSong() {
        model.getSong().setGenre(genre.getSelectedItemPosition());
        model.getSong().setTitle(title.getText().toString());
        model.getSong().setPerformer(performer.getText().toString());
        model.getSong().setAlbum(album.getText().toString());
        model.getSong().setYear_of_issue(Integer.parseInt(yearOfIssue.getText().toString()));
        model.getSong().setCreated(new Date().getTime());
        model.getSong().setUpdated(new Date().getTime());
        model.addNewSong();
        back();
    }

    private void defineUpdateAndDeleteSong() {
        Song song = model.getSong();
        newSong.setVisibility(View.GONE);
        genre.setSelection(song.getGenre());
        title.setText(song.getTitle());
        performer.setText(song.getPerformer());
        album.setText(song.getAlbum());
        yearOfIssue.setText(String.valueOf(song.getYear_of_issue()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String timeCreated = sdf.format(song.getCreated());
        String timeUpdated = sdf.format(song.getUpdated());
        created.setText(timeCreated);
        updated.setText(timeUpdated);

        updateSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSong();
            }
        });
        deleteSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSong();
            }
        });
    }

    private void updateSong() {
        model.getSong().setGenre(genre.getSelectedItemPosition());
        model.getSong().setTitle(title.getText().toString());
        model.getSong().setPerformer(performer.getText().toString());
        model.getSong().setAlbum(album.getText().toString());
        model.getSong().setYear_of_issue(Integer.parseInt(yearOfIssue.getText().toString()));
        model.getSong().setUpdated(new Date().getTime());
        model.updateSong();
        back();
    }

    private void deleteSong() {
        model.deleteSong();
        back();
    }

    @OnClick(R.id.back)
    public void back(){
        ((MainActivity)getActivity()).read();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Picasso.get().load(R.drawable.pop).into(image);
                break;
            case 1:
                Picasso.get().load(R.drawable.electronic).into(image);
                break;
            case 2:
                Picasso.get().load(R.drawable.rock).into(image);
                break;
            case 3:
                Picasso.get().load(R.drawable.soul).into(image);
                break;
            case 4:
                Picasso.get().load(R.drawable.reggae).into(image);
                break;
            case 5:
                Picasso.get().load(R.drawable.punk).into(image);
                break;
            case 6:
                Picasso.get().load(R.drawable.latin).into(image);
                break;
            case 7:
                Picasso.get().load(R.drawable.k_pop).into(image);
                break;
            case 8:
                Picasso.get().load(R.drawable.hip_hop).into(image);
                break;
            case 9:
                Picasso.get().load(R.drawable.country).into(image);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void alertInput(String title, String fill, TextView textView, int inputType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(title);

        final EditText input = new EditText(this.getActivity());
        input.setInputType(inputType);
        input.setText(fill);
        builder.setView(input);

        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(input.getText().toString());
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
