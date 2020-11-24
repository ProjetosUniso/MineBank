package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransferenciaActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

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

        Button transferenciaContasButton = findViewById(R.id.transferenciaContasButton);
        transferenciaContasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton.start();
                openTransferenciaContas();
            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openDeposito() {
        Intent intent = new Intent(this, DepositoActivity.class);
        startActivity(intent);
    }

    public void openSaque() {
        Intent intent = new Intent(this, SaqueActivity.class);
        startActivity(intent);
    }

    public void openTransferenciaContas() {
        Intent intent = new Intent(this, EntreContasCPFActivity.class);
        startActivity(intent);
    }
}