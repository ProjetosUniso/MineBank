package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntreContasValorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entre_contas_valor);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openTransferenciaContas();
            }
        });

        Button transferirValorButton = findViewById(R.id.transferirValorButton);
        transferirValorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openComprovanteTransferencia();
            }
        });
    }

    public void openTransferenciaContas() {
        Intent intent = new Intent(this, EntreContasCPFActivity.class);
        startActivity(intent);
    }

    public void openComprovanteTransferencia() {
        Intent intent = new Intent(this, ComprovanteTransferenciaActivity.class);
        startActivity(intent);
    }
}