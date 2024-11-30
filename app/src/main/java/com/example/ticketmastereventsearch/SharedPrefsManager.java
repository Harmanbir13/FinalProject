package com.example.ticketmastereventsearch;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {

    private static final String PREF_NAME = "TicketMasterPrefs"; // SharedPreferences file name
    private static final String KEY_LAST_CITY = "last_city";     // Key for saving last searched city

    // Save the last searched city in SharedPreferences
    public static void saveLastCity(Context context, String city) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAST_CITY, city);  // Save city as string
        editor.apply(); // Apply changes
    }

    // Get the last searched city from SharedPreferences
    public static String getLastCity(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LAST_CITY, "");  // Return last city or empty string if not found
    }

    // Clear the last searched city from SharedPreferences
    public static void clearLastCity(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_LAST_CITY);  // Remove the last city key
        editor.apply();  // Apply changes
    }
}
