package com.example.ticketmastereventsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<EventResponse.Event> eventList; // Use EventResponse.Event from your API response
    private final OnEventClickListener listener;

    // Constructor to pass the event list and listener from MainActivity
    public EventAdapter(List<EventResponse.Event> eventList, OnEventClickListener listener) {
        this.eventList = eventList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your custom item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        // Bind data to the view holder
        EventResponse.Event event = eventList.get(position);
        holder.tvEventName.setText(event.name);  // Assuming EventResponse.Event has a name field
        holder.tvEventDate.setText(event.dates.start.localDate);  // Assuming the event has a dates field

        // Set click listener for each item
        holder.itemView.setOnClickListener(v -> listener.onEventClick(event));
    }

    @Override
    public int getItemCount() {
        return eventList != null ? eventList.size() : 0;
    }

    // Method to update the event list
    public void setEvents(List<EventResponse.Event> events) {
        this.eventList = events;
        notifyDataSetChanged();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvEventName;
        private final TextView tvEventDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            tvEventName = itemView.findViewById(R.id.tvEventName); // Ensure this ID matches your layout
            tvEventDate = itemView.findViewById(R.id.tvEventDate); // Ensure this ID matches your layout
        }
    }

    // Define an interface for event item clicks
    public interface OnEventClickListener {
        void onEventClick(EventResponse.Event event);
    }
}
