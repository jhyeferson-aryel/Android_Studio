package com.unipac.jhyef.exsala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView campoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        campoView = (TextView) findViewById(R.id.campoView);

        ArrayList<Aluno> alunos = (ArrayList<Aluno>) bundle.get("informacoes");
        StringBuilder sb = new StringBuilder();
        for (Aluno a: alunos) {
            sb.append(a.toString());
        }

        campoView.setText(sb.toString());

    }
}
