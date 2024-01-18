package com.example.navesprit.reclamation.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navesprit.R;
import com.example.navesprit.db.Reclamation;
import com.example.navesprit.reclamation.DetailReclamationActivity;

import java.util.List;

public class ReclamationListAdminAdapter extends RecyclerView.Adapter<ReclamationListAdminAdapter.MyViewHolder> {

    private Context context;
    private List<Reclamation> recList;

    public ReclamationListAdminAdapter(Context context) {
        this.context = context;
    }

    public void setReclamationList(List<Reclamation> userList) {
        this.recList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvFirstName.setText(this.recList.get(position).userName);
        holder.tvLastName.setText(this.recList.get(position).description);

        // Set OnClickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailAdminActivity.class);
                intent.putExtra("username", recList.get(position).userName);
                intent.putExtra("desc", recList.get(position).description);
                intent.putExtra("etat", recList.get(position).etat);
                intent.putExtra("reclamation", recList.get(position));
                intent.putExtra("id", recList.get(position).uid);
                intent.putExtra("img", recList.get(position).image);
                intent.putExtra("date", recList.get(position).datereclam);
                intent.putExtra("heure", recList.get(position).heurereclam);


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.recList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName;
        TextView tvLastName;

        public MyViewHolder(View view) {
            super(view);
            tvFirstName = view.findViewById(R.id.tvSeanceId);
            tvLastName = view.findViewById(R.id.tvDateSeance);
        }
    }


}
