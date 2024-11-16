package com.aula.authcontrolapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {

    Button btnTentarNovamente, btnCriarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        btnTentarNovamente = findViewById(R.id.btnTentarNovamente);
        btnCriarCadastro = findViewById(R.id.btnCriarCadastro);

        // Volta para a tela de login
        btnTentarNovamente.setOnClickListener(v -> {
            finish(); // Fecha a ErrorActivity e volta para a MainActivity
        });

        // Vai para a tela de cadastro
        btnCriarCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(ErrorActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }
}
