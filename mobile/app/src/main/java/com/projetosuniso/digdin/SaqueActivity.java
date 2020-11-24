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

public class SaqueActivity extends Activity {

    private Conta conta;
    private final ContaService contaService = new ContaService();
    private final TipoMovimentacaoService serTpm = new TipoMovimentacaoService();
    private final HistMovimentacaoService serHtm = new HistMovimentacaoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        conta = contaService.getCPF(LoginActivity.cpf);

        TextView saldo = findViewById(R.id.saldo);
        saldo.setText( String.valueOf(conta.getSaldo()) );

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openTransferencia();
            }
        });

        Button saqueButton = findViewById(R.id.sacarButton);
        saqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                realizarDeposito();

            }
        });
    }

    public void openTransferencia() {
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }
    private String inserirData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormat.format(date);
    }

    //INCOMPLETO / NOT CHECKED
    private void realizarDeposito(){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = new TipoMovimentacao();

        EditText edtValor = findViewById(R.id.editTextVALORSAQUE);
        String valor = edtValor.getText().toString();

        //tpMoviment = serTpm.getID();
        tpMoviment.setChave("SAQUE");
        tpMoviment.setDescricao("Saque");
        tpMoviment.setId(1);

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setDescricao("saque");
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(conta);
        //movimentacao.setIdContaTransferencia();
        movimentacao.setDataInclusao(inserirData());

        String confirm = serHtm.adicionar(movimentacao);

        if (confirm.equals("exito")){
            Toast.makeText(this, "Saque Realizado com sucesso: " + confirm, Toast.LENGTH_LONG).show();
            conta.setSaldo( conta.getSaldo() - movimentacao.getValor() );

            contaService.atualizar(conta, conta.getId());

            openTransferencia();
        }else {
            Toast.makeText(this, "Houve erro ao realizar o saque: " + confirm, Toast.LENGTH_SHORT).show();
        }

    }
}