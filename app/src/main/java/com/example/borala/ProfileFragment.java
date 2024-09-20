package com.example.borala;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView profileName;
    private TextView profileDescription;
    private Button logoutButton;
    private Button favoriteAttractionsButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private static final int IMAGE_PICK_CODE = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        profileImage = rootView.findViewById(R.id.profile_image);
        profileName = rootView.findViewById(R.id.profile_name);
        profileDescription = rootView.findViewById(R.id.profile_description);
        logoutButton = rootView.findViewById(R.id.logout_button);
        favoriteAttractionsButton = rootView.findViewById(R.id.favorite_attractions_button);

        // Configurar o clique do botão de logout
        logoutButton.setOnClickListener(v -> logout());

        // Clique para abrir as atrações favoritas
        favoriteAttractionsButton.setOnClickListener(v -> openFavoriteAttractions());

        // Clique na imagem de perfil para escolher uma nova imagem
        profileImage.setOnClickListener(v -> openImagePicker());

        // Definir informações do perfil
        setProfileInfo();

        return rootView;
    }

    private void setProfileInfo() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            db.collection("users").document(user.getUid()).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User userInfo = document.toObject(User.class);
                                if (userInfo != null) {
                                    profileName.setText(userInfo.getName());
                                    profileDescription.setText(userInfo.getWhatsapp());
                                    // Se você tiver uma imagem de perfil, defina-a aqui
                                    // profileImage.setImageResource(R.drawable.profile_picture_placeholder);
                                }
                            }
                        } else {
                            // Lidar com erro
                        }
                    });
        }
    }

    private void logout() {
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish(); // Fechar a atividade atual
    }

    private void openFavoriteAttractions() {
        Intent intent = new Intent(getActivity(), FavoriteAttractionsActivity.class);
        startActivity(intent);
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            profileImage.setImageURI(imageUri);
            // Opcional: Upload da imagem no Firebase Storage
            // uploadProfileImage(imageUri);
        }
    }

    // Exemplo para upload da imagem no Firebase (opcional)
    private void uploadProfileImage(Uri imageUri) {
        // Adicione o código para subir a imagem no Firebase Storage
    }
}
