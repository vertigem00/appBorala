package com.example.borala.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.borala.Attraction;
import com.example.borala.R;
import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    private List<Attraction> attractions;
    private OnItemClickListener listener;

    // Interface para o listener de cliques
    public interface OnItemClickListener {
        void onItemClick(Attraction attraction);
    }

    // Construtor para aceitar a lista e o listener de cliques
    public AttractionAdapter(List<Attraction> attractions, OnItemClickListener listener) {
        this.attractions = attractions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attraction, parent, false);
        return new AttractionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        // Verifica se a lista de atrações não é nula
        if (attractions != null && !attractions.isEmpty()) {
            Attraction attraction = attractions.get(position);
            holder.attractionTitle.setText(attraction.getTitle());
            holder.attractionDescription.setText(attraction.getDescription());
            holder.attractionImage.setImageResource(attraction.getImageResId());

            // Atualiza o ícone de favorito com base no status atual
            holder.favoriteIcon.setImageResource(attraction.isFavorite() ? R.drawable.ic_favorite_fill : R.drawable.ic_favorite);

            // Configura o clique no ícone de favorito para alternar o estado
            holder.favoriteIcon.setOnClickListener(v -> {
                attraction.setFavorite(!attraction.isFavorite());
                notifyItemChanged(position);  // Atualiza o item após a mudança
            });

            // Configura o clique no item inteiro
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(attraction);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return attractions != null ? attractions.size() : 0;  // Verifica se a lista não é nula
    }

    static class AttractionViewHolder extends RecyclerView.ViewHolder {
        TextView attractionTitle, attractionDescription;
        ImageView attractionImage, favoriteIcon;

        AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            attractionTitle = itemView.findViewById(R.id.attraction_name);
            attractionDescription = itemView.findViewById(R.id.attraction_description);
            attractionImage = itemView.findViewById(R.id.attraction_image);
            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
        }
    }
}
