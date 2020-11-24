package com.projetosuniso.digdin;

import android.app.Activity;
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
    private Cliente cliente = new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        conta = contaService.getCPF(LoginActivity.cpf);
        cliente = conta.getCliente();

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

            saldoText.setText(String.valueOf( conta.getSaldo() ));
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