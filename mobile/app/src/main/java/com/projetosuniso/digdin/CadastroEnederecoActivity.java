package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroEnederecoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_enedereco);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroInfoPessoal();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroSenha();
            }
        });
    }

    public void openCadastroInfoPessoal() {
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity((intent));
    }

    public void openCadastroSenha() {
        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        startActivity((intent));
    }
}