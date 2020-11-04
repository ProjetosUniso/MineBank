package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroInfoPessoalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_info_pessoal);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroEmail();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroEndereco();
            }
        });
    }

    public void openCadastroEmail() {
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity(intent);
    }

    public void openCadastroEndereco() {
        Intent intent = new Intent(this, CadastroEnederecoActivity.class);
        startActivity((intent));
    }
}