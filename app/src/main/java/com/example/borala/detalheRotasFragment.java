package com.example.borala;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.borala.adapters.AttractionAdapter;
import android.util.Log;

import java.util.List;

public class detalheRotasFragment extends Fragment {

    private TourismPackage tourismPackage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalhesrotas, container, false);
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

        // Notificar o MainActivity de que estamos no fragmento de detalhes
        ((MainActivity) requireActivity()).setInDetailsFragment(true);
        Log.d("Rodou?", "Rodou: 1: ");

        // Recupera os argumentos passados
        if (getArguments() != null) {
            tourismPackage = (TourismPackage) getArguments().getSerializable("tourismPackage");
        }

        if (tourismPackage != null) {
            // Log para depuração
            Log.d("DetalheRotasFragment", "Pacote: " + tourismPackage.getTitle());

            // Configura o título do pacote turístico
            TextView titleTextView = view.findViewById(R.id.package_title_details);
            titleTextView.setText(tourismPackage.getTitle());

            // Configura o detalhe do pacote turístico
            TextView detailsTextView = view.findViewById(R.id.package_details);
            detailsTextView.setText(tourismPackage.getDetails());

            // Configura a imagem do pacote turístico
            ImageView packageImageView = view.findViewById(R.id.itinerary_image);
            packageImageView.setImageResource(tourismPackage.getImageResId());

            // Cria a lista de atrações do pacote
            List<Attraction> attractions = tourismPackage.getAttractions();
            Log.d("DetalheRotasFragment", "Atrações: " + (attractions != null ? attractions.size() : 0));

            // Configura o RecyclerView
            RecyclerView attractionsRecyclerView = view.findViewById(R.id.attractions_recyclerview);
            if (attractions != null && !attractions.isEmpty()) {
                // Cria o Adapter e configura no RecyclerView
                AttractionAdapter attractionAdapter = new AttractionAdapter(attractions, attraction -> {
                    // Ação ao clicar na atração
                });
                attractionsRecyclerView.setAdapter(attractionAdapter);
            } else {
                Log.e("DetalheRotasFragment", "No attractions found for the package");
            }

            // Configura o LayoutManager
            attractionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }

        // Configura o botão "Entre em contato e feche seu pacote"
        Button contactButton = view.findViewById(R.id.contact_button);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Número de telefone no formato internacional (ex: +5511999999999)
                String phoneNumber = "+5511999999999"; // Substitua pelo número desejado

                // Cria uma mensagem personalizada baseada no pacote
                String message = "Olá, estou interessado no pacote: " + tourismPackage.getTitle() +
                        ". Poderia me enviar mais informações?";

                // Codifica a mensagem para a URL
                String encodedMessage = Uri.encode(message);

                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + encodedMessage;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));

                // Verifica se há um aplicativo que pode abrir o link
                if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "WhatsApp não está instalado.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Notificar o MainActivity de que saímos do fragmento de detalhes
        ((MainActivity) requireActivity()).setInDetailsFragment(false);
    }
}
