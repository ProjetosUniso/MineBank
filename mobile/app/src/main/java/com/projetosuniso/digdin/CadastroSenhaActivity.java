package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;

import java.util.Random;

public class CadastroSenhaActivity extends  Activity {

    private final Cliente EXTRA_CLIENTE = new Cliente();
    private Cliente cliente;

    private final ContaService contaService = new ContaService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

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
                    openCadastroConcluido(Integer.parseInt(SENHA));
                }
                else {
                    editTextCONFIRMSENHA.setBackgroundResource(R.drawable.edittext_border);
                    textSENHASDIFERENTES.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void openCadastroEndereco() {
        Intent intent = new Intent(this, CadastroEnderecoActivity.class);
        startActivity((intent));
    }

    public void openCadastroConcluido(int Senha) {

        Conta conta = buildConta(Senha);
        conta.setCliente(cliente);

        contaService.adicionar(conta);

        Intent intent = new Intent(this, CadastroConcluidoActivity.class);
        intent.putExtra("conta", conta);
        startActivity((intent));
    }

    private Conta buildConta(int senha) {
        Conta conta = new Conta();

        int numero = generateRandomNumber();

        conta.setAgencia(1651);
        conta.setNumero(numero);
        conta.setSenha(senha);
        conta.setSaldo(0.00);
        conta.setPoupanca(0.00);

        return conta;
    }

    private int generateRandomNumber() {
        int max = 999999;
        int min = 100000;

        int random = (int)(Math.random() * (max - min + 1) + min);

        return random;
    }
}