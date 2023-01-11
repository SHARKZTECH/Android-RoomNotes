package com.example.roomnotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomnotes.db.AppDb;
import com.example.roomnotes.db.Notes;

public class AddNotesActivity extends AppCompatActivity {

    EditText titleInp,descInp;
    Button save;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        titleInp=findViewById(R.id.titleInp);
        descInp=findViewById(R.id.descInp);
        save=findViewById(R.id.saveBtn);

        long createdAt=System.currentTimeMillis();

        save.setOnClickListener(view -> {
            AppDb db=AppDb.getINSTANCE(this.getApplicationContext());
            Notes notes=new Notes();
            notes.createdAt=createdAt;
            notes.title=titleInp.getText().toString();
            notes.desc=descInp.getText().toString();
            db.notesDao().addNote(notes);
            Toast.makeText(this, "created!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}