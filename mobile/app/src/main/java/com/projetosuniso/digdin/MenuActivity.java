package com.projetosuniso.digdin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;

public class MenuActivity extends Activity {

    private int auxSaldo = 0;
    private final ContaService contaService = new ContaService();
    private Conta conta = new Conta();

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        conta = contaService.getCPF(LoginActivity.cpf);
        Cliente cliente = conta.getCliente();

        TextView usuario = findViewById(R.id.usuario);
        TextView codAgencia = findViewById(R.id.codAgencia);
        TextView codConta = findViewById(R.id.codConta);

        usuario.setText(String.valueOf( cliente.getNome() ));
        codAgencia.setText(String.valueOf( conta.getAgencia() ));
        codConta.setText(String.valueOf( conta.getNumero() ));


        TextView saldoText = findViewById(R.id.saldo);
        saldoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaldo();
            }
        });
    }


    public void showSaldo() {
        TextView saldoText = findViewById(R.id.saldo);
        if(auxSaldo == 0) {

            saldoText.setText(String.valueOf( conta.getSaldo() ));
            auxSaldo = 1;
        }
        else {
            saldoText.setText(R.string.saldo);
            auxSaldo = 0;
        }
    }

    public void openExit() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Encerrar sessão")
                .setMessage("Você tem certeza de que deseja sair da sua conta?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openLogin();
                    }
                })
                .setNegativeButton("Não", null)
                .show();

    }

    public void openLogin() {
        mediaPlayer.start();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openExtrato(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, ExtratoActivity.class);
        startActivity(intent);
    }

    public void openTransferencia(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }

    public void openPagamentos(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, PagamentosActivity.class);
        startActivity(intent);
    }

    public void openPoupanca(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, PoupancaActivity.class);
        startActivity(intent);
    }

}