package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroSenhaActivity extends  Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        final EditText editTextSENHA = findViewById(R.id.editTextSENHA);
        final EditText editTextCONFIRMSENHA = findViewById(R.id.editTextCONFIRMSENHA);

        final TextView textSENHASDIFERENTES = findViewById(R.id.textSenhasDiferentes);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroEndereco();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SENHA = editTextSENHA.getText().toString();
                String CONFIRMSENHA = editTextCONFIRMSENHA.getText().toString();

                clickButton.start();

                if(SENHA.matches(CONFIRMSENHA)){
                    openCadastroConcluido();
                }
                else {
                    editTextCONFIRMSENHA.setBackgroundResource(R.drawable.edittext_border);
                    textSENHASDIFERENTES.setVisibility(View.VISIBLE);
                }
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