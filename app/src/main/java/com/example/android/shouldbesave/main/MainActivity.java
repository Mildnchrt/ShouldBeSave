package com.example.android.shouldbesave.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.shouldbesave.R;
import com.example.android.shouldbesave.createNote.CreateNoteActivity;
import com.example.android.shouldbesave.data.NoteRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView{
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;

    private NoteRepository noteRepository;
    private MainPresenter presenter;
    private ListView lv;
    private TextView balance;

    private ListView lvNotes;
    private ArrayAdapter<String> notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = (ListView) findViewById(R.id.lvItems);
        noteRepository = NoteRepository.getInstance();
        presenter = new MainPresenter(noteRepository, this);
        balance = (TextView) findViewById(R.id.balance);

        init();

        presenter.start();

    }

    public void init() {
        lv = (ListView) findViewById(R.id.lvItems);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, CreateNoteActivity.class);
                i.putExtra("id", position).putExtra("requestCode", EDIT_REQUEST);
                startActivityForResult(i, EDIT_REQUEST);
            }
        });
    }


    public void create(View view) {
        Intent i = new Intent(this, CreateNoteActivity.class);
        i.putExtra("requestCode", ADD_REQUEST);
        startActivityForResult(i, ADD_REQUEST);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setShow(ArrayList<String> notes) {
        notesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        lvNotes.setAdapter(notesAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            presenter.updateShow();
        }
    }

    @Override
    public void setBalance(int balance) {
        this.balance.setText("Your balance:  " + balance + "  Baht");
    }
}
