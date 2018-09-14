package com.unipac.jhyef.exsala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nome, email, matricula;
    Button salvar, exibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Aluno> lista = new ArrayList<>();
        final Aluno aluno = new Aluno();

        nome = (EditText) findViewById(R.id.nome);
        matricula = (EditText) findViewById(R.id.matricula);
        email = (EditText) findViewById(R.id.email);
        salvar = (Button) findViewById(R.id.salvar);
        exibir = (Button) findViewById(R.id.exibir);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String campoNome = nome.getText().toString();
                String campoMatr = matricula.getText().toString();
                String campoEmail = email.getText().toString();

                aluno.setEmail(campoEmail);
                aluno.setMatricula(campoMatr);
                aluno.setNome(campoNome);

                lista.add(aluno);

                Toast.makeText(MainActivity.this, "Salvo!", Toast.LENGTH_LONG).show();
            }
        });
        exibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);

                i.putExtra("informacoes", lista);
                startActivity(i);
            }
        });
    }
}
