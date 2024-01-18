package com.example.navesprit.Seance.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navesprit.R;
import com.example.navesprit.db.SeanceWithChaises;

import java.util.List;

// SeanceAdapter.java
public class SeanceAdapter extends RecyclerView.Adapter<SeanceAdapter.SeanceViewHolder> {

    private List<SeanceWithChaises> seances;
    private OnSeanceClickListener onSeanceClickListener;



    public interface OnSeanceClickListener {
        void onSeanceClick(SeanceWithChaises seanceWithChaises);
    }

    public void setOnSeanceClickListener(OnSeanceClickListener listener) {
        this.onSeanceClickListener = listener;
    }

    public SeanceAdapter(List<SeanceWithChaises> seances) {
        this.seances = seances;
    }
    public void setData(List<SeanceWithChaises> seances) {
        this.seances = seances;
    }

    @NonNull
    @Override
    public SeanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rowww, parent, false);
        return new SeanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SeanceViewHolder holder, int position) {
        SeanceWithChaises seanceWithChaises = seances.get(position);
        holder.bind(seanceWithChaises);

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (onSeanceClickListener != null) {
                onSeanceClickListener.onSeanceClick(seanceWithChaises);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seances.size();
    }

    static class SeanceViewHolder extends RecyclerView.ViewHolder {

        private TextView numeroSeanceTextView;
        private TextView nomMatiereTextView;

        public SeanceViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroSeanceTextView = itemView.findViewById(R.id.tvSeanceId);
            nomMatiereTextView = itemView.findViewById(R.id.tvDateSeance);
        }

        public void bind(SeanceWithChaises seanceWithChaises) {
            numeroSeanceTextView.setText("Seance "+seanceWithChaises.seance.numero_seance);
            nomMatiereTextView.setText("Date "+seanceWithChaises.seance.date_seance);
            // Add more bindings as needed
        }
    }
}
