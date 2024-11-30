package com.example.ticketmastereventsearch;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(SavedEvent event);

    @Query("SELECT * FROM events")
    List<SavedEvent> getAllEvents();

    @Delete
    void deleteEvent(SavedEvent event);
}
