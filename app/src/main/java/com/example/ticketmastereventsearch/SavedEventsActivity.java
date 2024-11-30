package com.example.ticketmastereventsearch;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SavedEventsActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private SavedEventAdapter adapter;
    private EventDatabase database;
    private List<SavedEvent> savedEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_events);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewSavedEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Room database
        database = EventDatabase.getDatabase(getApplicationContext());

        // Fetch and display saved events
        fetchSavedEvents();
    }

    /**
     * Fetches saved events from the database and updates the RecyclerView.
     */
    private void fetchSavedEvents() {
        new Thread(() -> {
            savedEvents = database.eventDao().getAllEvents(); // Retrieve events from the database
            runOnUiThread(() -> {
                adapter = new SavedEventAdapter(savedEvents, event -> deleteEvent(event));
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    /**
     * Deletes an event from the database and updates the RecyclerView.
     *
     * @param event The event to be deleted.
     */
    private void deleteEvent(SavedEvent event) {
        new Thread(() -> {
            database.eventDao().deleteEvent(event); // Delete event from database
            runOnUiThread(() -> {
                savedEvents.remove(event); // Remove event from the list
                adapter.notifyDataSetChanged(); // Notify adapter about data change
                Toast.makeText(SavedEventsActivity.this, "Event deleted successfully", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}
