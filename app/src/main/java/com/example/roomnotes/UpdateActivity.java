package com.example.roomnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomnotes.db.AppDb;
import com.example.roomnotes.db.Notes;

public class UpdateActivity extends AppCompatActivity {
    EditText titleInp,descInp;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleInp=findViewById(R.id.titleInp);
        descInp=findViewById(R.id.descInp);
        save=findViewById(R.id.saveBtn);

        Notes note= (Notes) getIntent().getSerializableExtra("Note");
        titleInp.setText(note.title);
        descInp.setText(note.desc);

        long createdAt=System.currentTimeMillis();

        save.setOnClickListener(view -> {
            AppDb db=AppDb.getINSTANCE(this.getApplicationContext());
            Notes notes=new Notes();
            notes.uid=note.uid;
            notes.createdAt=createdAt;
            notes.title=titleInp.getText().toString();
            notes.desc=descInp.getText().toString();

            Log.d("Note",notes.desc);
            db.notesDao().updateNote(notes);
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
    }