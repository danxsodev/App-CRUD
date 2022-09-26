package com.example.atv_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalhesActivity extends AppCompatActivity {
    EditText editTextIdDet, editTextNomeDet, editTextFabricanteDet, editTextPrecoDet;
    Button buttonAlterar;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        editTextIdDet = findViewById(R.id.editTextIdDet);
        editTextNomeDet = findViewById(R.id.editTextNomeDet);
        editTextFabricanteDet = findViewById(R.id.editTextFabricanteDet);
        editTextPrecoDet = findViewById(R.id.editTextPrecoDet);
        buttonAlterar = findViewById(R.id.buttonAlterar);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        editTextNomeDet.setText(bundle.getString("nome"));
        editTextFabricanteDet.setText(bundle.getString("fabricante"));
        editTextPrecoDet.setText(bundle.getString("preco"));

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DtoProduto dtoProduto = new DtoProduto();
                dtoProduto.setId(id);
                dtoProduto.setNome(editTextIdDet.getText().toString());
                dtoProduto.setFabricante(editTextFabricanteDet.getText().toString());
                dtoProduto.setPreco(Double.parseDouble(editTextPrecoDet.getText().toString()));
                DaoProduto daoProduto = new DaoProduto(DetalhesActivity.this);
                try{
                    long linhasCadastradas = daoProduto.alterar(dtoProduto);
                    if(linhasCadastradas > 0){
                        Toast.makeText(DetalhesActivity.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(DetalhesActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                    else{
                        Toast.makeText(DetalhesActivity.this, "Não foi possiver alterar.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(DetalhesActivity.this, "Erro ao alterar" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}