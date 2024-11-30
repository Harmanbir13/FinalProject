package com.example.ticketmastereventsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

public class SavedEventAdapter extends RecyclerView.Adapter<SavedEventAdapter.ViewHolder> {

    private List<SavedEvent> events;
    private Consumer<SavedEvent> onDelete;

    public SavedEventAdapter(List<SavedEvent> events, Consumer<SavedEvent> onDelete) {
        this.events = events;
        this.onDelete = onDelete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_saved_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedEvent event = events.get(position);
        holder.name.setText(event.name);
        holder.deleteButton.setOnClickListener(v -> onDelete.accept(event));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventName);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
