package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransferenciaActivity extends Activity {

    private String tipo = "1";

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);
    }

    public void openMenuActivity(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openDeposito(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, DepositoActivity.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }

    public void openSaque(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, SaqueActivity.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }

    public void openTransferenciaContas(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, EntreContasCPFActivity.class);
        startActivity(intent);
    }
}