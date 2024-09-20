package com.example.borala.adapters;
import com.example.borala.CultureItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.borala.R;

import java.util.List;

public class CultureAdapter extends RecyclerView.Adapter<CultureAdapter.CultureViewHolder> {

    private List<CultureItem> cultureItems;

    public CultureAdapter(List<CultureItem> cultureItems) {
        this.cultureItems = cultureItems;
    }

    @NonNull
    @Override
    public CultureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.culture_item, parent, false);
        return new CultureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CultureViewHolder holder, int position) {
        CultureItem currentItem = cultureItems.get(position);
        holder.imageView.setImageResource(currentItem.getImageResId());
        holder.titleView.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return cultureItems.size();
    }

    public static class CultureViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleView;

        public CultureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.culture_item_image);
            titleView = itemView.findViewById(R.id.culture_item_title);
        }
    }
}
