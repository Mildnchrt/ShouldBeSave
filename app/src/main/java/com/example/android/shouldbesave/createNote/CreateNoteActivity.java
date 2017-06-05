package com.example.android.shouldbesave.createNote;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.shouldbesave.R;
import com.example.android.shouldbesave.data.NoteRepository;
import com.example.android.shouldbesave.main.MainActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.R.attr.description;
import static android.R.attr.onClick;
import static android.R.id.edit;
import static com.example.android.shouldbesave.R.id.money;

/**
 * Created by mildnchrt on 5/30/2017 AD.
 */

public class CreateNoteActivity extends AppCompatActivity implements CreateNoteView{
    private CreateNotePresenter presenter;
    private NoteRepository noteRepository;

    private EditText editTitleText;
    private EditText editDescriptionText;
    private EditText editMoneyText;
    private RadioButton income;
    private RadioButton outcome;
    private RadioGroup type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        noteRepository = NoteRepository.getInstance();
        int n = getIntent().getIntExtra("id", 0);
        presenter = new CreateNotePresenter(n, noteRepository, this);

        init();

        presenter.start();
    }

    public void init() {
        editTitleText = (EditText) findViewById(R.id.title);
        editDescriptionText = (EditText) findViewById(R.id.detail);
        editMoneyText = (EditText) findViewById(money);
        income = (RadioButton) findViewById(R.id.radioIncome);
        outcome = (RadioButton) findViewById(R.id.radioOutcome);
        type = (RadioGroup) findViewById(R.id.radioType);
    }


    public void submit(View view) {
        presenter.submit();
        finish();
    }

    public void back(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void delete(View view) {
        presenter.delete();
        finish();
    }

    @Override
    public String getTitleText() {
        return editTitleText.getText().toString();
    }

    @Override
    public String getDescriptionText() {
        return editDescriptionText.getText().toString();
    }

    @Override
    public int getMoneyText() {
        return Integer.parseInt(editMoneyText.getText().toString());
    }

    @Override
    public int getTypeText() {
        if(type.getCheckedRadioButtonId() == income.getId() && type.getCheckedRadioButtonId() != outcome.getId()) {
            return 1;
        }
        else if(type.getCheckedRadioButtonId() != income.getId() && type.getCheckedRadioButtonId() == outcome.getId()) {
            return -1;
        }
        return 0;
    }

    @Override
    public void setEditTitle(String t) {
        editTitleText.setText(t);
    }

    @Override
    public void setEditDescription(String d) {
        editDescriptionText.setText(d);
    }

    @Override
    public void setEditMoney(int m) {
        editMoneyText.setText(m+"");
    }

    @Override
    public void setEditType(int t) {
        Log.d("type---> ", t+"");
        if(t == 1) {
            income.setChecked(true);
            outcome.setChecked(false);
        }
        else if(t == -1) {
            Log.d("hello ", t+"");
            income.setChecked(false);
            outcome.setChecked(true);
        }
    }

    @Override
    public void setEditTitleHint(String t) {
        editTitleText.setHint(t);
    }

    @Override
    public void setEditDescriptionHint(String d) {
        editDescriptionText.setHint(d);
    }

    @Override
    public void setEditMoneyHint(String m) {
        editMoneyText.setHint(m);
    }

    @Override
    public void setEdiTypeHint() {
        income.setChecked(true);
        outcome.setChecked(false);
    }
}
