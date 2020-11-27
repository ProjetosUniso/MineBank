package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;

public class PoupancaActivity extends Activity {

    private final ContaService contaService = new ContaService();
    private final String tipo = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupanca);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Conta conta = contaService.getCPF(LoginActivity.cpf);

        TextView saldoText = findViewById(R.id.saldo);
        saldoText.setText(String.valueOf( conta.getPoupanca() ));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
            }
        });

        Button depositoButton = findViewById(R.id.depositoButton);
        depositoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton.start();
                openDeposito();
            }
        });

        Button saqueButton = findViewById(R.id.saqueButton);
        saqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton.start();
                openSaque();
            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openDeposito() {
        Intent intent = new Intent(this, DepositoActivity.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }

    public void openSaque() {
        Intent intent = new Intent(this, SaqueActivity.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }
}