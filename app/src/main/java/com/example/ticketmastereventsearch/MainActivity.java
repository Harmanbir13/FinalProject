package com.example.ticketmastereventsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ticketmastereventsearch.EventResponse;
import com.example.ticketmastereventsearch.SharedPrefsManager;

import java.util.ArrayList;
import java.util.Locale;
// prince is nu start kari eh run kari
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etCity, etRadius;
    private Button btnSearch, btnSavedEvents;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;  // Adapter for RecyclerView
    private String lastSearchedCity;
    private EventDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCity = findViewById(R.id.etCity);
        etRadius = findViewById(R.id.etRadius);
        btnSearch = findViewById(R.id.btnSearch);
        btnSavedEvents = findViewById(R.id.btnSavedEvents);
        recyclerView = findViewById(R.id.rvEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Room database
        database = EventDatabase.getDatabase(getApplicationContext());


        eventAdapter = new EventAdapter(new ArrayList<>(), new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(EventResponse.Event event) {
                new Thread(() -> {
                    SavedEvent savedEvent = new SavedEvent(event.name ,
                            event.url,
                            event.dates.start.localDate,0.0 , 0.0,
                            event.images.get(0).url);
                    database.eventDao().insert(savedEvent);
                }).start();
            }
        });  // Initialize your event adapter
        recyclerView.setAdapter(eventAdapter);

        // Get last searched city from SharedPreferences
        lastSearchedCity = SharedPrefsManager.getLastCity(this);
        if (!lastSearchedCity.isEmpty()) {
            etCity.setText(lastSearchedCity);
        }

        // Search Button Click
        btnSearch.setOnClickListener(v -> {
            String city = etCity.getText().toString();
            String radiusStr = etRadius.getText().toString();

            if (city.isEmpty() || radiusStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both city and radius", Toast.LENGTH_SHORT).show();
                return;
            }

            int radius = Integer.parseInt(radiusStr);
            searchEvents(city, radius);

            // Save the city for future launches
            SharedPrefsManager.saveLastCity(MainActivity.this, city);
        });

        // Saved Events Button Click
        btnSavedEvents.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SavedEventsActivity.class);
            startActivity(intent);
        });
    }

    // Method to search events from Ticketmaster API
    private void searchEvents(String city, int radius) {
        String apiKey = "GGmkXwtklVOKJN8C7qNZ3CFwtKdwzALk";  // Replace with your actual API key


        TicketmasterApi ticketmasterApi = RetrofitClient.getClient(this).create(TicketmasterApi.class);

        Call<EventResponse> call = ticketmasterApi.getEvents(apiKey, city.toLowerCase(Locale.ROOT), radius);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EventResponse eventResponse = response.body();
                    if (eventResponse._embedded != null && eventResponse._embedded.events != null) {
                        eventAdapter.setEvents(eventResponse._embedded.events);  // Set events in adapter
                    } else {
                        Toast.makeText(MainActivity.this, "No events found.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error fetching events", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
