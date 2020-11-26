package com.projetosuniso.digdin;

import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;

public class ComprovanteTransferenciaActivity extends Activity {

    private String cpfCre;
    private String valor;
    private final ContaService contaService = new ContaService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_transferencia);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        cpfCre = getIntent().getStringExtra("cpfTransferencia");
        valor = getIntent().getStringExtra("valorTransferencia");

        atualizarPlaca();

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void atualizarPlaca(){

        Conta contaDev = contaService.getCPF(LoginActivity.cpf);
        Cliente clienteDev = contaDev.getCliente();

        Conta contaCre = contaService.getCPF(cpfCre);
        Cliente clienteCre = contaCre.getCliente();

        TextView nomeClienteDev = findViewById(R.id.nomeDevedorText);
        nomeClienteDev.setText(clienteDev.getNome());
        TextView contaClienteDev = findViewById(R.id.contaDevedorText);
        contaClienteDev.setText("Conta: " + contaDev.getAgencia() + " | "+ "Agencia: " + contaDev.getAgencia());

        TextView nomeClienteCre = findViewById(R.id.nomeCredorText);
        nomeClienteCre.setText(clienteCre.getNome());
        TextView contaClienteCre = findViewById(R.id.contaCredorText);
        contaClienteCre.setText("Conta: " + contaCre.getAgencia() + " | "+ "Agencia: " + contaCre.getAgencia());

        TextView valorTrs = findViewById(R.id.valorRecebidoText);
        valorTrs.setText(valor);
    }
}