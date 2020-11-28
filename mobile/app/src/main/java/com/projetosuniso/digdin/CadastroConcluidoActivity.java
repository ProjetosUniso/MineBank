package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.utils.Email.JavaEmailService;

public class CadastroConcluidoActivity extends Activity {

    private final Conta conta = new Conta();

    private MediaPlayer mediaPlayer = null;

    JavaEmailService javaEmail = new JavaEmailService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_concluido);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);
        Conta conta = (Conta) getIntent().getSerializableExtra("conta");

        if (conta != null) {
            String email = conta.getCliente().getEmail();
            String subject = "Bem vindo, ao MineBank!";
            // Criar uma menssagem para enviar no email
            String mensagem = "Olá " + conta.getCliente().getNome() + "! Você acaba de concluir seu cadastro no Minebank, o maior banco de esmeraldas do MUNDO!\nConfira os dados da sua conta abaixo:" +
                    "\n\n=-=-=-=-=-=-=-=-=-=-=-=\n" +
                    "Nome:" + conta.getCliente().getNome() + " " + conta.getCliente().getSobrenome() + "\nAgencia:" + conta.getAgencia() + "\nConta:" + conta.getNumero() +
                    "\n=-=-=-=-=-=-=-=-=-=-=-=" +
                    "\n\n Agradecemos a preferência!\n\nAssinado: Equipe Minebank.";

            javaEmail.EnviarEmailCadastro(this, email, subject, mensagem);
        }
    }

    public void openLogin(View view) {
        mediaPlayer.start();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}