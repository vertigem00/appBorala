package com.example.borala.adapters;
import com.example.borala.NatureItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.borala.R;

import java.util.List;

public class NatureAdapter extends RecyclerView.Adapter<NatureAdapter.NatureViewHolder> {

    private List<NatureItem> natureItems;

    public NatureAdapter(List<NatureItem> natureItems) {
        this.natureItems = natureItems;
    }

    @NonNull
    @Override
    public NatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nature_item, parent, false);
        return new NatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NatureViewHolder holder, int position) {
        NatureItem currentItem = natureItems.get(position);
        holder.imageView.setImageResource(currentItem.getImageResId());
        holder.titleView.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return natureItems.size();
    }

    public static class NatureViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleView;

        public NatureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nature_item_image);
            titleView = itemView.findViewById(R.id.nature_item_title);
        }
    }
}
