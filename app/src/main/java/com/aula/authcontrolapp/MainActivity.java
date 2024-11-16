package com.aula.authcontrolapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editUsuario, editSenha;
    Button btnEntrar;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        editUsuario = findViewById(R.id.editUsuario);
        editSenha = findViewById(R.id.editSenha);
        btnEntrar = findViewById(R.id.btnEntrar);

        // Ação do botão "Entrar"
        btnEntrar.setOnClickListener(v -> {
            String usuario = editUsuario.getText().toString();
            String senha = editSenha.getText().toString();

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Por favor, preencha todos os campos.",
                        Toast.LENGTH_LONG).show();
            } else {
                verificarLogin(usuario, senha);
            }
        });
    }

    private void verificarLogin(String usuario, String senha) {
        Cursor cursor = dbHelper.obterSenhaPorUsuario(usuario);
        if (cursor != null && cursor.moveToFirst()) {
            String senhaDb = cursor.getString(0);
            cursor.close();

            if (senha.equals(senhaDb)) {
                // Se as credenciais estiverem corretas, vai para a tela "OK"
                Intent intent = new Intent(MainActivity.this, OkActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Senha incorreta, vai para a tela "ERRO"
                Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            // Se o usuário não for encontrado, vai para a tela de erro
            Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
