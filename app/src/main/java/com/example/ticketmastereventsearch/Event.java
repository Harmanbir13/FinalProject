package com.example.ticketmastereventsearch;

public class Event {
    private String name;
    private String url;
    private String date;
    private double priceMin;
    private double priceMax;
    private String imageUrl;

    // Constructor
    public Event(String name, String url, String date, double priceMin, double priceMax, String imageUrl) {
        this.name = name;
        this.url = url;
        this.date = date;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.imageUrl = imageUrl;
    }

    // Getter methods
    public String getName() { return name; }
    public String getUrl() { return url; }
    public String getDate() { return date; }
    public double getPriceMin() { return priceMin; }
    public double getPriceMax() { return priceMax; }
    public String getImageUrl() { return imageUrl; }
}
