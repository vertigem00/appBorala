package com.example.borala;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextWhatsApp;
    private EditText editTextPassword;
    private Button buttonRegister;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextWhatsApp = findViewById(R.id.edit_text_whatsapp);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonRegister = findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String whatsapp = editTextWhatsApp.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || whatsapp.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            User userInfo = new User(name, whatsapp);
                            db.collection("users").document(user.getUid())
                                    .set(userInfo)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(RegisterActivity.this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(RegisterActivity.this, "Erro ao registrar usuário", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Erro ao registrar usuário", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
