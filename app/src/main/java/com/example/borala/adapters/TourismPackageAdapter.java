package com.example.borala.adapters;

import com.example.borala.TourismPackage;
import com.example.borala.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TourismPackageAdapter extends RecyclerView.Adapter<TourismPackageAdapter.TourismViewHolder> {

    private List<TourismPackage> tourismPackages;
    private OnItemClickListener listener;

    // Construtor para passar a lista de pacotes de turismo e o listener
    public TourismPackageAdapter(List<TourismPackage> tourismPackages, OnItemClickListener listener) {
        this.tourismPackages = tourismPackages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TourismViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar o layout do item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tourism_package_item, parent, false);
        return new TourismViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourismViewHolder holder, int position) {
        // Verificar se a lista de pacotes de turismo não está vazia
        if (tourismPackages != null && !tourismPackages.isEmpty()) {
            // Configurar os dados do pacote de turismo para o item do layout
            TourismPackage currentPackage = tourismPackages.get(position);
            holder.imageView.setImageResource(currentPackage.getImageResId());
            holder.titleView.setText(currentPackage.getTitle());

            // Configurar o listener de clique
            holder.itemView.setOnClickListener(v -> listener.onItemClick(currentPackage));
        }
    }

    @Override
    public int getItemCount() {
        return tourismPackages != null ? tourismPackages.size() : 0;  // Verifica se a lista não é nula
    }

    // ViewHolder para armazenar as views de cada item
    public static class TourismViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleView;

        public TourismViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.package_image);
            titleView = itemView.findViewById(R.id.package_title);
        }
    }

    // Interface para clique no item
    public interface OnItemClickListener {
        void onItemClick(TourismPackage tourismPackage);
    }
}
