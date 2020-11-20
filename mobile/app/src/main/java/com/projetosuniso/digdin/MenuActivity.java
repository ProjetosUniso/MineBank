package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.projetosuniso.digdin.model.Conta;

public class MenuActivity extends Activity {

    private int auxSaldo = 0;
    private final Conta EXTRA_CONTA = new Conta();
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        conta = (Conta) getIntent().getSerializableExtra("conta");

        TextView saldoText = findViewById(R.id.saldo);
        saldoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaldo();
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openLogin();
            }
        });

        Button extratoButton = findViewById(R.id.extratoButton);
        extratoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openExtrato();
            }
        });

        Button transferenciaButton = findViewById(R.id.transferenciaButton);
        transferenciaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openTransferencia();
            }
        });
    }

    public void showSaldo() {
        TextView saldoText = findViewById(R.id.saldo);
        if(auxSaldo == 0) {

            saldoText.setText("475,55");
            auxSaldo = 1;
        }
        else {
            saldoText.setText(R.string.saldo);
            auxSaldo = 0;
        }

    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openExtrato() {
        Intent intent = new Intent(this, ExtratoActivity.class);
        startActivity(intent);
    }

    public void openTransferencia() {
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }

}