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

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poupanca);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        Conta conta = contaService.getCPF(LoginActivity.cpf);

        TextView saldoText = findViewById(R.id.saldo);
        saldoText.setText(String.valueOf( conta.getPoupanca() ));
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
}