package com.example.atv_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText editTextId, editTextNome, editTextFabricante, editTextPreco;
    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar2);
        editTextId = findViewById(R.id.editTextId);
        editTextNome = findViewById(R.id.editTextNome);
        editTextFabricante = findViewById(R.id.editTextFabricante);
        editTextPreco = findViewById(R.id.editTextPreco);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(view -> {
            DtoProduto dtoProduto = new DtoProduto();
            dtoProduto.setNome(editTextId.getText().toString());
            dtoProduto.setFabricante(editTextFabricante.getText().toString());
            dtoProduto.setPreco(Double.parseDouble(editTextPreco.getText().toString()));
            DaoProduto daoProduto = new DaoProduto(MainActivity2.this);
            try{
                long linhasCadastradas = daoProduto.inserir(dtoProduto);
                if(linhasCadastradas > 0){
                    Toast.makeText(MainActivity2.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(main);
                }
                else{
                    Toast.makeText(MainActivity2.this, "Não foi possiver cadastrar.", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception ex){
                Toast.makeText(MainActivity2.this, "Erro ao cadastrar:" + ex.toString(), Toast.LENGTH_SHORT).show();
            }

            });
        };
    }
