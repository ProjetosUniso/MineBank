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

public class DepositoActivity extends Activity {

    private Conta conta;
    private String valor;
    private String aux;
    private int tipo;
    private final ContaService contaService = new ContaService();
    private final TipoMovimentacaoService serTpm = new TipoMovimentacaoService();
    private final HistMovimentacaoService serHtm = new HistMovimentacaoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        aux = getIntent().getStringExtra("tipo");
        tipo = Integer.parseInt(aux);

        conta = contaService.getCPF(LoginActivity.cpf);

        TextView saldo = findViewById(R.id.saldo);
        if (tipo == 1) {
            saldo.setText( String.valueOf(conta.getSaldo()) );
        }
        else if (tipo == 2) {
            saldo.setText( String.valueOf(conta.getPoupanca()) );
        }

        Button depositarButton = findViewById(R.id.depositarButton);
        depositarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();

                EditText edtValor = findViewById(R.id.editTextVALORDEPOSITO);
                valor = edtValor.getText().toString();
                if (valor.equals("") || valor.equals("0")){
                    Toast.makeText(DepositoActivity.this, "Insira uma quantidade valida", Toast.LENGTH_SHORT).show();
                } else {
                    realizarDeposito();
                }

            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                if (tipo == 1) {
                    openTransferencia();
                }
                else {
                    openPoupanca();
                }

            }
        });
    }

    public void openTransferencia() {
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }

    public void openPoupanca() {
        Intent intent = new Intent(this, PoupancaActivity.class);
        startActivity(intent);
    }

    private String inserirData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormat.format(date);
    }

    private void realizarDeposito(){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = serTpm.getChave("DEPOSITO");

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(conta);
        movimentacao.setIdContaTransferencia(conta.getId());
        movimentacao.setDataInclusao(inserirData());

        if (tipo == 1){
            movimentacao.setDescricao("deposito - conta corrente");
        } else if (tipo == 2){
            movimentacao.setDescricao("deposito - poupanca");
        }

        String confirm = serHtm.adicionar(movimentacao);

        if (confirm.equals("exito")){
            Toast.makeText(this, "Deposito Realizado com sucesso: " + confirm, Toast.LENGTH_LONG).show();

            if (tipo == 1) {
                int novoSaldo = (int) (conta.getSaldo() + Double.parseDouble(valor));
                contaService.atualizarSaldo(conta.getId(), novoSaldo);
            }
            else if (tipo == 2) {
                int novoSaldo = (int) (conta.getPoupanca() + Double.parseDouble(valor));
                contaService.atualizaPoupanca( conta.getId(), novoSaldo);
            }
            openTransferencia();
        }else {
            Toast.makeText(this, "Houve erro ao realizar o deposito: " + confirm, Toast.LENGTH_SHORT).show();
        }

    }
}