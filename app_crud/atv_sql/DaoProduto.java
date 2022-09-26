package com.example.atv_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DaoProduto extends SQLiteOpenHelper {
    private final String tabela = "tb_produto";

    public DaoProduto(@Nullable Context context) {
        super(context, "db_produto", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String comando = "create table " + tabela + "(" +
                "id integer primary key," +
                "nome varchar(50) not null," +
                "fabricante varchar(30)," +
                "preco decimal(10,2))";

        sqLiteDatabase.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long inserir(DtoProduto produto) {
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("fabricante", produto.getFabricante());
        values.put("preco", produto.getPreco());

        return getWritableDatabase().insert(tabela, null, values);
    }

    public ArrayList<DtoProduto> consultarTudo(){
        String comando = "select * from " + tabela;
        Cursor cursor = getWritableDatabase().rawQuery(comando, null);
        ArrayList<DtoProduto> arrayListProduto = new ArrayList<>();

        while (cursor.moveToNext()){
            DtoProduto dtoProduto = new DtoProduto();
            dtoProduto.setId((cursor.getInt(0)));
            dtoProduto.setNome((cursor.getString(1)));
            dtoProduto.setFabricante((cursor.getString(2)));
            dtoProduto.setPreco((cursor.getDouble(3)));

            arrayListProduto.add(dtoProduto);
        }

        return arrayListProduto;
    }

    public ArrayList<DtoProduto> consultarPorNome(String nome){
        String comando = "select * from " + tabela + " where nome like ?"  ;
        String[] args = {"%" + nome + "%"};
        Cursor cursor = getWritableDatabase().rawQuery(comando, args);
        ArrayList<DtoProduto> arrayListProduto = new ArrayList<>();

        while (cursor.moveToNext()){
            DtoProduto dtoProduto = new DtoProduto();
            dtoProduto.setId((cursor.getInt(0)));
            dtoProduto.setNome((cursor.getString(1)));
            dtoProduto.setFabricante((cursor.getString(2)));
            dtoProduto.setPreco((cursor.getDouble(3)));

            arrayListProduto.add(dtoProduto);
        }

        return arrayListProduto;
    }
    public int excluir(DtoProduto produto) {
        String id = "id=?";
        String[] args = {produto.getId()+""};
         return getWritableDatabase().delete(tabela,id,args);
    }

    public long alterar(DtoProduto produto) {
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("fabricante", produto.getFabricante());
        values.put("preco", produto.getPreco());
        String id = "id=?";
        String[] args = {produto.getId()+""};
        return getWritableDatabase().update(tabela, values, id, args);
    }
}
