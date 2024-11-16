package com.aula.authcontrolapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OkActivity extends AppCompatActivity {

    private Button btnAdministrarUsuarios;  // Botão para administrar usuários

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok);  // A tela principal após login bem-sucedido

        // Inicializa o botão
        btnAdministrarUsuarios = findViewById(R.id.btnAdministrarUsuarios);

        // Configura o clique do botão
        btnAdministrarUsuarios.setOnClickListener(v -> {
            // Ao clicar no botão, abre a tela de gestão de usuários
            Intent intent = new Intent(OkActivity.this, UserManagementActivity.class);
            startActivity(intent);
        });
    }
}
