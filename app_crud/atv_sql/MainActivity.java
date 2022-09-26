package com.example.atv_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewProd;
    Button buttonCadastrarNovo;
    ArrayList<DtoProduto> arrayListProduto = new ArrayList<>();
    EditText editTextPesquisaNome;
    DaoProduto daoProduto = new DaoProduto(MainActivity.this);
    DtoProduto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProd = findViewById(R.id.listViewProd);
        buttonCadastrarNovo = findViewById(R.id.buttonCadastrarNovo);
        editTextPesquisaNome = findViewById(R.id.editTextPesquisaNome);
        listViewProd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                produto = arrayListProduto.get(posicao);
                registerForContextMenu(listViewProd);
                return false;
            }
        });

        editTextPesquisaNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                arrayListProduto = daoProduto.consultarPorNome(editable.toString());
                atualizarListView();
            }
        });

        buttonCadastrarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrar = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(cadastrar);
            }
        });

        arrayListProduto = daoProduto.consultarTudo();
        atualizarListView();
    }

    private void atualizarListView() {
        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrayListProduto);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0, "Detalhes / alterar");
        menu.add(0,1,1, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==0){
            Intent intent = new Intent(MainActivity.this,DetalhesActivity.class);
            intent.putExtra("id",produto.getId());
            intent.putExtra("nome", produto.getNome());
            intent.putExtra("fabricante", produto.getFabricante());
            intent.putExtra("preco", produto.getPreco());
                    startActivity(intent);
        }

        else {
            AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
            msg.setMessage("Confirma a exclusão?");
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int deletados = daoProduto.excluir(produto);
                    if (deletados > 0){


                        Toast.makeText(MainActivity.this, "Excluido com sucesso", Toast.LENGTH_SHORT).show();
                        arrayListProduto = daoProduto.consultarTudo();
                        atualizarListView();

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Ocorreu algum erro ao excluir", Toast.LENGTH_SHORT).show();
                    }
                }

            });
            msg.setNegativeButton("Não",null);
            msg.show();
        }


        return super.onContextItemSelected(item);
    }
}