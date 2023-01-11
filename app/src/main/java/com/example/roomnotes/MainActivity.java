package com.example.roomnotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.roomnotes.db.AppDb;
import com.example.roomnotes.db.Notes;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    List<Notes>  notesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter=new NotesAdapter(this);
        recyclerView.setAdapter(notesAdapter);

        loadNotes();
        notesAdapter.setNotesList(notesList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent=new Intent(MainActivity.this,AddNotesActivity.class);
                startActivityForResult(intent,100);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadNotes(){
        AppDb db=AppDb.getINSTANCE(this.getApplicationContext());
        notesList=db.notesDao().getAllNotes();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
           loadNotes();
           notesAdapter.setNotesList(notesList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
        notesAdapter.setNotesList(notesList);
    }
}