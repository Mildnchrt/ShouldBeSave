package com.example.android.shouldbesave.main;

import android.widget.ListView;

import com.example.android.shouldbesave.data.Note;
import com.example.android.shouldbesave.data.NoteRepository;

import java.util.ArrayList;

/**
 * Created by mildnchrt on 5/30/2017 AD.
 */

public class MainPresenter {
    private NoteRepository repository;
    private MainView view;

    public MainPresenter(NoteRepository repository, MainView view) {
        this.repository = repository;
        this.view = view;
    }

    public void start() {
        updateShow();
    }

    public void updateShow() {
        ArrayList<String> s = new ArrayList<String>();
        Note currentNote;
        int balance = 0;
        for(int i = 0; i < repository.getNoteCount(); i++) {
            balance += repository.getNoteAt(i).getMoney() * repository.getNoteAt(i).getType();
            currentNote = repository.getNoteAt(i);
            s.add(currentNote.getTitle());
        }
        view.setShow(s);
        view.setBalance(balance);
    }
}
