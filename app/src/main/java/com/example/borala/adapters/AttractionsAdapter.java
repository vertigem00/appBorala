package com.example.borala;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.AttractionViewHolder> {

    private List<String> attractionsList;

    public AttractionsAdapter(List<String> attractionsList) {
        this.attractionsList = attractionsList;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attraction, parent, false);
        return new AttractionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        String attraction = attractionsList.get(position);
        holder.attractionName.setText(attraction);
    }

    @Override
    public int getItemCount() {
        return attractionsList.size();
    }

    static class AttractionViewHolder extends RecyclerView.ViewHolder {
        TextView attractionName;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            attractionName = itemView.findViewById(R.id.attraction_name);
        }
    }
}
