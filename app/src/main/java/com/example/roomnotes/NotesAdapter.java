package com.example.roomnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomnotes.db.AppDb;
import com.example.roomnotes.db.Notes;

import java.text.DateFormat;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    List<Notes> notesList;
    Context context;

    public NotesAdapter(Context context) {
        this.context = context;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(notesList.get(position).title);
        holder.desc.setText(notesList.get(position).desc);
        String formatedDate= DateFormat.getDateTimeInstance().format(notesList.get(position).createdAt);
        holder.date.setText(formatedDate);

        holder.itemView.setOnLongClickListener(view -> {
            PopupMenu menu=new PopupMenu(context,view);
            menu.getMenu().add("Delete");
            menu.setOnMenuItemClickListener(item -> {
                if (item.getTitle().equals("Delete")){
                    AppDb db=AppDb.getINSTANCE(context);
                    db.notesDao().delNotes(notesList.get(holder.getAdapterPosition()));
                    notesList.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                }
                return true;
            });
            menu.show();
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.desc);
            date=itemView.findViewById(R.id.date);
        }
    }
}
