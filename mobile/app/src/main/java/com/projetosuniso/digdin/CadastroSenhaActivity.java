package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroSenhaActivity extends  Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroEndereco();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroConcluido();
            }
        });
    }

    public void openCadastroEndereco() {
        Intent intent = new Intent(this, CadastroEnederecoActivity.class);
        startActivity((intent));
    }

    public void openCadastroConcluido() {
        Intent intent = new Intent(this, CadastroConcluidoActivity.class);
        startActivity((intent));
    }
}