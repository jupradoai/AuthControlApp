package com.aula.authcontrolapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class UserManagementActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    TextInputEditText textUsuario, textSenha;
    TextView textResultado;
    Button btnGravar, btnConsultar, btnAtualizar, btnDeletar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        dbHelper = new DatabaseHelper(this);

        textUsuario = findViewById(R.id.textInEdUserT1);
        textSenha = findViewById(R.id.textInEdSenhaT1);
        textResultado = findViewById(R.id.textResultadoT1);
        btnGravar = findViewById(R.id.btnGravarT1);
        btnConsultar = findViewById(R.id.btnConsultarT1);
        btnAtualizar = findViewById(R.id.btnAtualizarT1);
        btnDeletar = findViewById(R.id.btnDeletarT1);

        // Botão Gravar
        btnGravar.setOnClickListener(v -> {
            String usuario = textUsuario.getText().toString();
            String senha = textSenha.getText().toString();

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(UserManagementActivity.this,
                        "Preencha todos os campos.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Aqui, senha é mantida como String, sem tentar converter para int
            if (dbHelper.inserirDados(usuario, senha)) {
                Toast.makeText(UserManagementActivity.this,
                        "Dados inseridos com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(UserManagementActivity.this,
                        "Erro ao inserir dados.",
                        Toast.LENGTH_LONG).show();
            }
        });


        // Botão Consultar
        btnConsultar.setOnClickListener(v -> {
            String usuario = textUsuario.getText().toString();

            if (usuario.isEmpty()) {
                Toast.makeText(UserManagementActivity.this,
                        "Usuário Inválido",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Cursor cursor = dbHelper.obterSenhaPorUsuario(usuario);
            if (cursor != null && cursor.moveToFirst()) {
                String senha = cursor.getString(0);
                textResultado.setText("Senha: " + senha);
                cursor.close();
            } else {
                Toast.makeText(UserManagementActivity.this,
                        "Usuário não encontrado.",
                        Toast.LENGTH_LONG).show();
            }
        });

        // Botão Atualizar
        btnAtualizar.setOnClickListener(v -> {
            String usuario = textUsuario.getText().toString();
            String senha = textSenha.getText().toString();

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(UserManagementActivity.this,
                        "Preencha todos os campos.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Aqui, senha é mantida como String, sem tentar convertê-la para int
            if (dbHelper.atualizarDados(usuario, senha)) {  // Passando a senha como String
                Toast.makeText(UserManagementActivity.this,
                        "Senha atualizada com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(UserManagementActivity.this,
                        "Usuário não encontrado.",
                        Toast.LENGTH_LONG).show();
            }
        });

        // Botão Deletar
        btnDeletar.setOnClickListener(v -> {
            String usuario = textUsuario.getText().toString();

            if (usuario.isEmpty()) {
                Toast.makeText(UserManagementActivity.this,
                        "Usuário inválido",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if (dbHelper.deletarDados(usuario)) {
                Toast.makeText(UserManagementActivity.this,
                        "Usuário deletado com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(UserManagementActivity.this,
                        "Usuário não encontrado.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
