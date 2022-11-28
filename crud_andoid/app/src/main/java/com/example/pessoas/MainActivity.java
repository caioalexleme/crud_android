package com.example.pessoas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText nome;
    public EditText cpf;
    public EditText endereco;
    public EditText telefone;
    private PessoaDAO dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        endereco = findViewById(R.id.editEndereco);
        telefone = findViewById(R.id.editTelefone);
        dao = new PessoaDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("pessoa")){
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            nome.setText(pessoa.getNome());
            cpf.setText(pessoa.getCpf());
            endereco.setText(pessoa.getEndereco());
            telefone.setText(pessoa.getTelefone());

        }
    }

    public void salvar(View view){

        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(cpf.getText().toString());
            pessoa.setEndereco(endereco.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida com id: " + id, Toast.LENGTH_SHORT).show();
        }else {
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(cpf.getText().toString());
            pessoa.setEndereco(endereco.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            dao.atualizar(pessoa);
            Toast.makeText(this, "Pessoa foi atualizada", Toast.LENGTH_SHORT).show();
        }
    }
}