package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.utils.Email.JavaEmailService;
import com.projetosuniso.digdin.utils.Email.JavaMailAPI;

public class CadastroConcluidoActivity extends Activity {

    private final Cliente EXTRA_CLIENTE = new Cliente();

    JavaEmailService javaEmail = new JavaEmailService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_concluido);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openLogin();
            }
        });

        if (cliente != null) {
            String email = cliente.getEmail();
            String subject = "Bem vindo, ao MineBank!";
            // Criar uma menssagem para enviar no email
            String mensagem = "Leonardo \nAgencia: 1651 \nConta: 123123312";

            javaEmail.EnviarEmailCadastro(this, email, subject, mensagem);
        }
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}