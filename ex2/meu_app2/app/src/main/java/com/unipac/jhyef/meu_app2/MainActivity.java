package com.unipac.jhyef.meu_app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView inputGas, inputEt;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputGas = (TextView) findViewById(R.id.gasolina);
        inputEt = (TextView) findViewById(R.id.etanol);
        btnCalc = (Button) findViewById(R.id.calculo);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Tratativa para lógica*/
                try{

                   Double x = Double.parseDouble(inputGas.getText().toString());
                   Double y = Double.parseDouble(inputEt.getText().toString());
                   Double r = (x * 70) / 100;

                    /*Condição para retornar respota ao usuário*/
                    if(y <= r){
                        Toast.makeText(MainActivity.this, "Experimente Etanol!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Experimente Gasolina", Toast.LENGTH_SHORT).show();
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Insira um formato decimal válido!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
