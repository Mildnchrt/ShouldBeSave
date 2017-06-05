package com.example.android.shouldbesave.createNote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.shouldbesave.data.Note;
import com.example.android.shouldbesave.data.NoteRepository;
import com.example.android.shouldbesave.main.MainActivity;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by mildnchrt on 5/30/2017 AD.
 */

public class CreateNotePresenter {
    private NoteRepository repository;
    private CreateNoteView view;

    private int currentIndex;
    private int requestCode;

    public CreateNotePresenter(int index,NoteRepository noteRepository, CreateNoteView view) {
        this.currentIndex = index;
        this.repository = noteRepository;
        this.view = view;
    }

    public void start() {
        Bundle extra = ((CreateNoteActivity) view).getIntent().getExtras();
        requestCode = extra.getInt("requestCode");
        Log.d("Show Waht--> ", requestCode+"");
        if(requestCode == MainActivity.ADD_REQUEST) {
            view.setEditTitleHint("Title");
            view.setEditDescriptionHint("detail");
            view.setEditMoneyHint("how much...");
            view.setEdiTypeHint();
        }
        else if (requestCode == MainActivity.EDIT_REQUEST) {
            Note currentNote = repository.getNoteAt(currentIndex);

            view.setEditTitle(currentNote.getTitle());
            view.setEditDescription(currentNote.getDescription());
            view.setEditMoney(currentNote.getMoney());
            view.setEditType(currentNote.getType());
        }
    }

    public void submit() {
        Intent returnedIntentg = new Intent();
        if(requestCode == MainActivity.ADD_REQUEST) {
            int numberOfNotes = repository.save(new Note(view.getTitleText(), view.getDescriptionText(), view.getMoneyText(), view.getTypeText()));
            returnedIntentg.putExtra("numberOfNote", numberOfNotes);
        }
        else if (requestCode == MainActivity.EDIT_REQUEST) {
            Note currentNote = repository.getNoteAt(currentIndex);
            currentNote.setTitle(view.getTitleText());
            currentNote.setDescription(view.getDescriptionText());
            currentNote.setMoney(view.getMoneyText());
            currentNote.setType(view.getTypeText());
        }
        ((CreateNoteActivity) view).setResult(Activity.RESULT_OK, returnedIntentg);
    }

    public void delete() {
        Intent returnedIntentg = new Intent();
        if(requestCode == MainActivity.EDIT_REQUEST) {
            int numberOfNotes = repository.delete(currentIndex);
            returnedIntentg.putExtra("numberOfNote", numberOfNotes);
        }
        ((CreateNoteActivity) view).setResult(Activity.RESULT_OK, returnedIntentg);
    }
}
