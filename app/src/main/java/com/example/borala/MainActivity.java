
package com.example.borala;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import com.example.borala.R;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private boolean isRoutesFragment = true; // Assume "Rotas" é o fragmento inicial
    private boolean isInDetailsFragment = false; // Controle do fragmento de detalhes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar DrawerLayout e NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configurar a Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Configurar o ícone do menu lateral (drawer)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configurar NavigationView (menu lateral)
        navigationView.setNavigationItemSelectedListener(item -> {
            // Lógica para o item selecionado no menu lateral
            drawerLayout.closeDrawer(GravityCompat.START); // Fechar o menu lateral após a seleção
            return true;
        });

        // Configurar o fragmento inicial (Rotas)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RoutesFragment())
                    .commit();
        }

        // Configurar o BottomNavigationView
        // Configurar o BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.nav_diarie) {
                selectedFragment = new diarieFragment(); // Certifique-se de que o nome da classe está correto
                isRoutesFragment = false; // Atualizar o estado
                isInDetailsFragment = false; // Não estamos em um fragmento de detalhes
            } else if (item.getItemId() == R.id.nav_routes) {
                selectedFragment = new RoutesFragment();
                isRoutesFragment = true; // Atualizar o estado
                isInDetailsFragment = false; // Não estamos em um fragmento de detalhes
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
                isRoutesFragment = false; // Atualizar o estado
                isInDetailsFragment = false; // Não estamos em um fragmento de detalhes
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                updateDrawerToggle(); // Atualizar o ícone do menu lateral
                return true;
            }
            return false;
        });

        // Selecionar o item inicial no BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.nav_routes);
    }

    @Override
    public void onBackPressed() {
        // Fechar o drawer se estiver aberto ao pressionar o botão voltar
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Somente abrir o drawer se o fragmento atual for "Rotas"
        if (item.getItemId() == android.R.id.home) {
            if (isInDetailsFragment) {
                // Voltar ao fragmento anterior
                onBackPressed();
            } else if (isRoutesFragment) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateDrawerToggle() {
        // Atualizar o ícone de navegação com base no estado do fragmento
        if (isInDetailsFragment) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back); // Ícone de voltar
        } else if (isRoutesFragment) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu); // Ícone do menu lateral
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void setInDetailsFragment(boolean inDetailsFragment) {
        isInDetailsFragment = inDetailsFragment;
        updateDrawerToggle();
    }
}
