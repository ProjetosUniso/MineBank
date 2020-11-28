package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.HistMovimentacaoService;
import com.projetosuniso.digdin.service.TipoMovimentacaoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaqueActivity extends Activity {

    private Conta conta;
    private String valor;
    private int tipo;
    private final ContaService contaService = new ContaService();
    private final TipoMovimentacaoService serTpm = new TipoMovimentacaoService();
    private final HistMovimentacaoService serHtm = new HistMovimentacaoService();

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        String tipoExtra = getIntent().getStringExtra("tipo");
        if (tipoExtra != null) {
            tipo = Integer.parseInt(tipoExtra);
        }

        conta = contaService.getCPF(LoginActivity.cpf);

        TextView saldo = findViewById(R.id.saldo);
        if (tipo == 1) {
            saldo.setText( String.valueOf(conta.getSaldo()) );
        }
        else if (tipo == 2) {
            saldo.setText( String.valueOf(conta.getPoupanca()) );
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tipo == 1) {
                    openMenu();
                }
                else if (tipo == 2) {
                    openPoupanca();
                }
            }
        });

        Button saqueButton = findViewById(R.id.sacarButton);
        saqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edtValor = findViewById(R.id.editTextVALORSAQUE);
                valor = edtValor.getText().toString();
                if (valor.equals("") || valor.equals("0")){
                    Toast.makeText(SaqueActivity.this, "Insira uma quantidade valida", Toast.LENGTH_SHORT).show();
                } else {
                    realizarSaque();
                }
            }
        });
    }

    public void openMenu() {
        mediaPlayer.start();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openPoupanca() {
        mediaPlayer.start();
        Intent intent = new Intent(this, PoupancaActivity.class);
        startActivity(intent);
    }

    private String inserirData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    private void realizarSaque(){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = serTpm.getChave("SAQUE");

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(conta);
        movimentacao.setIdContaTransferencia(conta.getId());
        movimentacao.setDataInclusao(inserirData());

        if (tipo == 1){
            movimentacao.setDescricao("Saque");
        }else if ( tipo == 2 ){
            movimentacao.setDescricao("Resgate - Poupanca");
        }

        String confirm = serHtm.adicionar(movimentacao);

        if (confirm.equals("exito")){
            Toast.makeText(this, "Saque Realizado com sucesso: " + confirm, Toast.LENGTH_LONG).show();

            if (tipo == 1) {
                int novoSaldo = (int) ( conta.getSaldo() - movimentacao.getValor() );
                contaService.atualizarSaldo(conta.getId(), novoSaldo);

                openMenu();
            }
            else if (tipo == 2) {
                int novaPoupanca = (int) ( conta.getPoupanca() - movimentacao.getValor() );
                int novoSaldo = (int) ( conta.getSaldo() + movimentacao.getValor() );

                contaService.atualizaPoupanca(conta.getId(), novaPoupanca);
                contaService.atualizarSaldo(conta.getId(), novoSaldo);

                openPoupanca();
            }

        }else {
            Toast.makeText(this, "Houve erro ao realizar o saque: " + confirm, Toast.LENGTH_SHORT).show();
        }

    }
}