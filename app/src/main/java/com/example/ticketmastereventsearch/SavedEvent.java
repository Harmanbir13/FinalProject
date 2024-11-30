package com.example.ticketmastereventsearch;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class SavedEvent {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String url;
    public String date;
    public double priceMin;
    public double priceMax;
    public String imageUrl;

    public SavedEvent(String name, String url, String date, double priceMin, double priceMax, String imageUrl) {
        this.name = name;
        this.url = url;
        this.date = date;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.imageUrl = imageUrl;
    }
}
