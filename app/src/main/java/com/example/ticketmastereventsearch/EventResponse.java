package com.example.ticketmastereventsearch;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EventResponse {

    @SerializedName("_embedded")
    public Embedded _embedded;

    // Embedded class to hold events
    public static class Embedded {
        @SerializedName("events")
        public List<Event> events;  // List of events fetched from the API
    }

    // Event model class
    public static class Event {
        @SerializedName("name")
        public String name;         // Event name

        @SerializedName("dates")
        public Date dates;          // Event date

        @SerializedName("priceRanges")
        public List<PriceRange> priceRanges;  // Event price range

        @SerializedName("url")
        public String url;          // URL for ticket purchase

        @SerializedName("images")
        public List<Image> images;  // List of images related to the event
    }

    // Date class to hold the event start date
    public static class Date {
        @SerializedName("start")
        public Start start;

        // Start class to parse start date
        public static class Start {
            @SerializedName("localDate")
            public String localDate;  // Event start date
        }
    }

    // PriceRange class to parse the price range for events
    public static class PriceRange {
        @SerializedName("min")
        public double minPrice;   // Minimum price for tickets

        @SerializedName("max")
        public double maxPrice;   // Maximum price for tickets
    }

    // Image class to parse the promotional image URL
    public static class Image {
        @SerializedName("url")
        public String url;        // Image URL for the event
    }
}
