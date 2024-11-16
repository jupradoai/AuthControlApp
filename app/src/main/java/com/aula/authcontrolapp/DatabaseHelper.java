package com.aula.authcontrolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Constantes do DB:
    public static final String DATABASE_NAME = "dados.db";
    public static final String TABLE_NAME = "pessoas";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USUARIO";
    public static final String COL_3 = "SENHA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Mudança: Alterei o tipo da coluna SENHA de INTEGER para TEXT
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USUARIO TEXT, SENHA TEXT)");  // SENHA agora é TEXT
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método para inserir dados (a senha é agora uma String, não um int)
    public boolean inserirDados(String usuario, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, usuario);
        contentValues.put(COL_3, senha);  // Armazenando senha como String
        long resultado = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return resultado != -1;
    }

    // Método para consultar a senha de um usuário
    public Cursor obterSenhaPorUsuario(String usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_3};  // Agora apenas a senha
        String selection = COL_2 + " = ?";
        String[] selectionArgs = {usuario};
        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    // Método para atualizar a senha
    public boolean atualizarDados(String usuario, String novaSenha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, novaSenha);  // Atualizar a coluna SENHA com uma String
        int linhasAfetadas = db.update(TABLE_NAME, contentValues, COL_2 + " = ?", new String[]{usuario});
        db.close();
        return linhasAfetadas > 0;
    }


    // Método para deletar um usuário
    public boolean deletarDados(String usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        int linhasAfetadas = db.delete(TABLE_NAME, COL_2 + " = ?", new String[]{usuario});
        db.close();
        return linhasAfetadas > 0;
    }
}
