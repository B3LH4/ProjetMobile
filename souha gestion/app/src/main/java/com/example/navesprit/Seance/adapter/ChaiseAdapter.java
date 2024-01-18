package com.example.navesprit.Seance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navesprit.R;
import com.example.navesprit.db.Chaise;

import java.util.List;

public class ChaiseAdapter extends RecyclerView.Adapter<ChaiseAdapter.ChaiseViewHolder> {

    private List<Chaise> chaises;
    private Context context; // Add a Context variable
    private ChaiseClickListener chaiseClickListener;

    // Constructor with Context and ChaiseClickListener parameters
    public ChaiseAdapter(List<Chaise> chaises, Context context, ChaiseClickListener chaiseClickListener) {
        this.chaises = chaises;
        this.context = context;
        this.chaiseClickListener = chaiseClickListener;
    }

    @NonNull
    @Override
    public ChaiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_seance, parent, false);
        return new ChaiseViewHolder(itemView, (ChaiseClickListener) parent.getContext());
    }


    @Override
    public void onBindViewHolder(@NonNull ChaiseViewHolder holder, int position) {
        Chaise chaise = chaises.get(position);
        holder.bind(chaise);
    }

    @Override
    public int getItemCount() {
        return chaises.size();
    }

    static class ChaiseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvSeanceId;
        private TextView tvDisponibility;
        private ImageView ivSeanceImage;
        private Chaise currentChaise;
        private ChaiseAdapter.ChaiseClickListener chaiseClickListener; // Corrected variable name


        public ChaiseViewHolder(@NonNull View itemView,ChaiseAdapter.ChaiseClickListener listener) {
            super(itemView);
            tvSeanceId = itemView.findViewById(R.id.tvSeanceId);
            tvDisponibility = itemView.findViewById(R.id.tvDateSeance);
            ivSeanceImage = itemView.findViewById(R.id.ivSeanceImage);
            chaiseClickListener = listener;
            itemView.setOnClickListener(this);        }

        public void bind(Chaise chaise) {
            currentChaise = chaise;
            if (chaise.disponibilite_chaise.equals("Available")) {
                ivSeanceImage.setImageResource(R.drawable.baseline_seat_available);
                tvSeanceId.setText("Chaise:" + chaise.numero_chaise);
                tvDisponibility.setText(String.valueOf( chaise.disponibilite_chaise));
            } else {
                ivSeanceImage.setImageResource(R.drawable.baseline_seat_unavailable);
                tvSeanceId.setText("Chaise:" + chaise.numero_chaise);
                tvDisponibility.setText(String.valueOf(chaise.disponibilite_chaise));
            }
        }

        @Override
        public void onClick(View view) {
            // Use the ChaiseClickListener to handle the click event
            if (chaiseClickListener != null) {
                chaiseClickListener.onChaiseClick(currentChaise);
            }
        }
    }

    // Define the ChaiseClickListener interface
    public interface ChaiseClickListener {
        void onChaiseClick(Chaise chaise);
    }
}
