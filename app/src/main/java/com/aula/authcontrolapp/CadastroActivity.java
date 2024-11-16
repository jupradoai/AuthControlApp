package com.aula.authcontrolapp;

import android.content.Intent; // Importar Intent para navegação
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    // Atualize os IDs conforme o XML
    EditText textUsuario, textSenha;
    Button btnCadastrar;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new DatabaseHelper(this);

        // Use os IDs corretos para os campos de usuário e senha
        textUsuario = findViewById(R.id.editUsuario);  // Corrigido para editUsuario
        textSenha = findViewById(R.id.editSenha);      // Corrigido para editSenha
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {
            String usuario = textUsuario.getText().toString();
            String senha = textSenha.getText().toString();

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(CadastroActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Agora estamos passando a senha como String diretamente
            boolean cadastroBemSucedido = dbHelper.inserirDados(usuario, senha);  // Corrigido para passar a senha como String

            if (cadastroBemSucedido) {
                Toast.makeText(CadastroActivity.this, "Cadastro bem-sucedido!", Toast.LENGTH_SHORT).show();

                // Criar um Intent para ir para a tela de Login
                Intent loginIntent = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(loginIntent);  // Iniciar a LoginActivity

                // Fechar a CadastroActivity para que o usuário não volte para ela ao pressionar o botão de voltar
                finish();
            } else {
                Toast.makeText(CadastroActivity.this, "Erro no cadastro!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
