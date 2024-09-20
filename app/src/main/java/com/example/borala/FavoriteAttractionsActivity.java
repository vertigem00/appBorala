package com.example.borala;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.borala.AttractionsAdapter;
import java.util.ArrayList;
import java.util.List;

public class FavoriteAttractionsActivity extends AppCompatActivity {

    private RecyclerView attractionsRecyclerView;
    private AttractionsAdapter attractionsAdapter;
    private List<String> favoriteAttractions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_attractions);

        attractionsRecyclerView = findViewById(R.id.attractions_recycler_view);
        attractionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Exemplo de lista de atrações favoritas (pode ser preenchido a partir do Firestore)
        favoriteAttractions = new ArrayList<>();
        favoriteAttractions.add("Atração 1");
        favoriteAttractions.add("Atração 2");
        favoriteAttractions.add("Atração 3");

        // Configurar o adapter para a RecyclerView
        attractionsAdapter = new AttractionsAdapter(favoriteAttractions);
        attractionsRecyclerView.setAdapter(attractionsAdapter);
    }
}
