package com.esprit.rentacar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.rentacar.dao.SalleDao;
import com.esprit.rentacar.entity.Salles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SalleAdapter extends RecyclerView.Adapter<SalleAdapter.TransactionViewHolder> {
    private List<Salles> salles = new ArrayList<>();
    private SalleDao salleDao;
    public SalleAdapter(SalleDao salleDao){this.salleDao=salleDao;}

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.salle_item, parent, false);
        return new TransactionViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Salles salle = salles.get(position);
        holder.bind(salle);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Salles transactionToDelete = salles.get(position);
                    salleDao.delete(transactionToDelete);
                    salles.remove(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return salles.size();
    }

    public void setSalles(List<Salles> transactions) {
        this.salles = transactions;
        notifyDataSetChanged();
    }


    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView fb_contenue,fb_Desc,fb_gps,fb_date,fb_rating;
        private ImageView btn_delete,item_update;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            fb_contenue = itemView.findViewById(R.id.fb_contenue);
            fb_Desc = itemView.findViewById(R.id.fb_Desc);
            fb_gps = itemView.findViewById(R.id.fb_gps);
            fb_date = itemView.findViewById(R.id.fb_date);
            fb_rating = itemView.findViewById(R.id.fb_rating);
            btn_delete = itemView.findViewById(R.id.item_delete2);
            item_update = itemView.findViewById(R.id.item_update2);

        }

            public void bind(Salles salles) {
            fb_contenue.setText(salles.getNom_salle());
            fb_Desc.setText(salles.getDesc_salle());
            fb_gps.setText(salles.getGps_salle());

            // Check if the date string is not null or empty before formatting
            if (salles.getDate() != null && !salles.getDate().isEmpty()) {
                // Parse the date string
                SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                try {
                    Date date = inputFormat.parse(salles.getDate());
                    fb_date.setText(outputFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle parsing exception if needed
                    fb_date.setText("N/A");
                }
            } else {
                fb_date.setText("N/A");
            }

            fb_rating.setText(String.valueOf(salles.getPrix_salle()));
            int connectedUserId = 1;
            if (salles.getUserId() == connectedUserId) {
                btn_delete.setVisibility(View.VISIBLE);
                item_update.setVisibility(View.VISIBLE);

            } else {
                btn_delete.setVisibility(View.GONE);
                item_update.setVisibility(View.GONE);
            }

            item_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), UpdateSalle.class);
                    intent.putExtra("SALLE_ID", salles.getIdsalle());
                    itemView.getContext().startActivity(intent);
                }
            });
        }


    }

















}
