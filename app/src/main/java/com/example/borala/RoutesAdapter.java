package com.example.borala;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.RouteViewHolder> {

    private ArrayList<String> routesList;

    public RoutesAdapter(ArrayList<String> routesList) {
        this.routesList = routesList;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        String route = routesList.get(position);
        holder.routeTextView.setText(route);
    }

    @Override
    public int getItemCount() {
        return routesList.size();
    }

    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        TextView routeTextView;

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            routeTextView = itemView.findViewById(R.id.routeTextView);
        }
    }
}
