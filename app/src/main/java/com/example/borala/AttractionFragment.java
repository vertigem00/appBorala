package com.example.borala;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AttractionFragment extends Fragment {

    private String attractionTitle;
    private String attractionDescription;
    private int attractionImageResId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attraction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configura a Toolbar
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Recupera os dados da atração
        if (getArguments() != null) {
            attractionTitle = getArguments().getString("attractionTitle");
            attractionDescription = getArguments().getString("attractionDescription");
            attractionImageResId = getArguments().getInt("attractionImageResId");
        }

        // Configura os elementos da UI
        TextView titleTextView = view.findViewById(R.id.attraction_title);
        titleTextView.setText(attractionTitle);

        TextView descriptionTextView = view.findViewById(R.id.attraction_description);
        descriptionTextView.setText(attractionDescription);

        ImageView imageView = view.findViewById(R.id.attraction_image);
        imageView.setImageResource(attractionImageResId);

        // Notificar o MainActivity de que estamos no fragmento de detalhes
        ((MainActivity) requireActivity()).setInDetailsFragment(true);
    }
}
