package com.example.ticketmastereventsearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TicketmasterApi {

    // API endpoint to fetch events
    @GET("discovery/v2/events.json")
    Call<EventResponse> getEvents(
            @Query("apikey") String apiKey,  // API key (to be replaced with your own)
            @Query("city") String city,     // City name for the search
            @Query("radius") int radius     // Search radius in kilometers
    );
}
