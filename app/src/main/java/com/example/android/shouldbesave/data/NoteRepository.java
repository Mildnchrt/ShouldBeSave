package com.example.android.shouldbesave.data;

import java.util.ArrayList;

/**
 * Created by mildnchrt on 5/30/2017 AD.
 */

public class NoteRepository {
    private ArrayList<Note> notes;

    static NoteRepository singleInstance = null;

    static public NoteRepository getInstance() {
        if(singleInstance == null) {
            singleInstance = new NoteRepository();
        }
        return singleInstance;
    }

    private NoteRepository() {
        notes = new ArrayList<Note>();

        // for early testing
        notes.add(new Note("CAT","a very cute animal", 10, 1));
        notes.add(new Note("TIGER","a very big cat", 20, -1));
        notes.add(new Note("ELEPHANT","a very big animal with a long nose", 30, -1));
    }

    public int getNoteCount() {
        return notes.size();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Note getNoteAt(int index) {
        return notes.get(index);
    }

    public int save(Note card) {
        notes.add(card);
        return notes.size();
    }

    public int delete(int index) {
        notes.remove(index);
        return notes.size();
    }

}
