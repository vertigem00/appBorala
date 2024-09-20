package com.example.borala;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.borala.adapters.AttractionAdapter;
import com.example.borala.Attraction;
import com.example.borala.adapters.TourismPackageAdapter;
import com.google.android.material.navigation.NavigationView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;


public class RoutesFragment extends Fragment {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private RecyclerView tourismRecyclerView;
    private RecyclerView natureRecyclerView;
    private RecyclerView cultureRecyclerView;
    private TourismPackageAdapter tourismAdapter;
    private AttractionAdapter natureAdapter;
    private AttractionAdapter cultureAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_routes, container, false);

        // Acessar o DrawerLayout e NavigationView da atividade
        drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
        navigationView = requireActivity().findViewById(R.id.nav_view);
        toolbar = requireActivity().findViewById(R.id.toolbar);

        // Configurar a Toolbar
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            }
        }

        // Configurar o menu lateral (NavigationView)
        navigationView.setNavigationItemSelectedListener(item -> {
            // Ações de filtragem
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Configurar RecyclerView para Pacotes de Turismo
        tourismRecyclerView = rootView.findViewById(R.id.tourism_packages_recyclerview);
        // Criar lista de pacotes de turismo com as atrações
        List<Attraction> attractions = new ArrayList<>();
        List<TourismPackage> tourismPackages = new ArrayList<>();

        attractions.add(new Attraction(R.drawable.quixada, "Trilha da Galinha Choca", "Trilha muito famosa na região, num dos monólitos mais conhecidos", new ArrayList<>()));
        attractions.add(new Attraction(R.drawable.cafe_cego_aderaldo, "Café Cultural Cego Aderaldo", "Experiência gastronômica local", new ArrayList<>()));
        attractions.add(new Attraction(R.drawable.fazenda_nao_me_deixes, "Fazenda Não me Deixes", "Fazenda onde viveu Rachel de Queiroz", new ArrayList<>()));
        attractions.add(new Attraction(R.drawable.peixada_imperial, "Peixada Imperial", "Almoço local na beira do cedro", new ArrayList<>()));

        tourismPackages.add(new TourismPackage(R.drawable.belezaquixas, "Belezas de Quixadá",
                                "Explore as maravilhas naturais e culturais de Quixadá nesta rota única.\n" +
                                        "Preço: R$ 800 com Passagens, Hospedagem e Alimentação (dois almoços) incluso.", attractions));

        tourismPackages.add(new TourismPackage(R.drawable.estevao, "Serra do Estevão", "Detalhes sobre Serra do Estevão", new ArrayList<>()));

        // Configurar o adapter
        tourismAdapter = new TourismPackageAdapter(tourismPackages, tourismPackage -> {
            Fragment detailsFragment = new detalheRotasFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("tourismPackage", tourismPackage); // Certifique-se de usar putSerializable
            detailsFragment.setArguments(bundle);
            Log.d("RoutesFragment", "Tentando passar o pacote: " + tourismPackage.getTitle());

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        });
        tourismRecyclerView.setAdapter(tourismAdapter);
        tourismRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Lista de atrações de natureza
        List<Attraction> natureAttractions = new ArrayList<>();
        natureAttractions.add(new Attraction(R.drawable.quixada, "Trilha da Galinha Choca", "Trilha muito famosa na região, num dos monólitos mais conhecidos", new ArrayList<>()));
        natureAttractions.add(new Attraction(R.drawable.cruzeiro, "Pedra do Cruzeiro", "Pedra que fica no centro da cidade e tem uma cruz em seu topo.", new ArrayList<>()));
        natureAttractions.add(new Attraction(R.drawable.cedro, "Açude Cedro", "Açude histórico da região.", new ArrayList<>()));

        // Configurar RecyclerView para Natureza
        natureRecyclerView = rootView.findViewById(R.id.nature_recyclerview);
        natureAdapter = new AttractionAdapter(natureAttractions, attraction -> {
            AttractionFragment attractionFragment = new AttractionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("attractionTitle", attraction.getTitle());
            bundle.putString("attractionDescription", attraction.getDescription());
            bundle.putInt("attractionImageResId", attraction.getImageResId()); // Método para obter o ID da imagem
            attractionFragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, attractionFragment)
                    .addToBackStack(null)
                    .commit();
        });
        natureRecyclerView.setAdapter(natureAdapter);
        natureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Lista de atrações de cultura
        List<Attraction> cultureAttractions = new ArrayList<>();
        cultureAttractions.add(new Attraction(R.drawable.fazenda_nao_me_deixes, "Fazenda Não me Deixes", "Fazenda onde viveu Rachel de Queiroz", new ArrayList<>()));
        cultureAttractions.add(new Attraction(R.drawable.chale_da_pedra, "Praça do Chalé", "Praça que é um marco cultural da cidade, onde se encontra a Fundação Cultural.", new ArrayList<>()));
        cultureAttractions.add(new Attraction(R.drawable.cafe_cego_aderaldo, "Casa de Saberes Cego Aderaldo", "Aparelho cultural do estado do Ceará", new ArrayList<>()));

        // Configurar RecyclerView para Cultura
        cultureRecyclerView = rootView.findViewById(R.id.culture_recyclerview);
        cultureAdapter = new AttractionAdapter(cultureAttractions, attraction -> {
            Fragment attractionFragment = new AttractionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("attractionTitle", attraction.getTitle());
            bundle.putString("attractionDescription", attraction.getDescription());
            bundle.putInt("attractionImageResId", attraction.getImageResId()); // Método para obter o ID da imagem
            attractionFragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, attractionFragment)
                    .addToBackStack(null)
                    .commit();
        });
        cultureRecyclerView.setAdapter(cultureAdapter);
        cultureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
