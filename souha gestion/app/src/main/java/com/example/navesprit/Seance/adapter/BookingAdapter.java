package com.example.navesprit.Seance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navesprit.R;
import com.example.navesprit.db.Booking;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.SeanceWithChaises;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookings;
    private OnBookingClickListener onBookingClickListener;

    public interface OnBookingClickListener {
        void onBookingClick(Booking booking);
    }

    public void setOnBookingClickListener(OnBookingClickListener listener) {
        this.onBookingClickListener = listener;
    }

    public BookingAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setData(List<Booking> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rowww, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.bind(booking);

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (onBookingClickListener != null) {
                onBookingClickListener.onBookingClick(booking);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {

        private TextView numeroSeanceTextView;
        private TextView nomMatiereTextView;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroSeanceTextView = itemView.findViewById(R.id.tvSeanceId);
            nomMatiereTextView = itemView.findViewById(R.id.tvDateSeance);
        }

        public void bind(Booking booking) {
            numeroSeanceTextView.setText("user: "+booking.nom_user);
            nomMatiereTextView.setText("heure: "+booking.heure_seance);
            // Add more bindings as needed
        }
    }
}
