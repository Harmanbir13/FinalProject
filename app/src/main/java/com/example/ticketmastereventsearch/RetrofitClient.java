package com.example.ticketmastereventsearch;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// done sir //saved button daa dekhi ki miss kitta main
public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://app.ticketmaster.com/";

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            // Creating Gson object for custom configurations if needed
            Gson gson = new GsonBuilder()
                    .setLenient()  // Allows lenient parsing of malformed JSON (if needed)
                    .create();

            // Building Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Base URL for API requests
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Gson converter
                    .build();
        }
        return retrofit;
    }
}
