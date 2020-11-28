package com.projetosuniso.digdin;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.HistMovimentacaoService;
import com.projetosuniso.digdin.service.TipoMovimentacaoService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EntreContasValorActivity extends Activity {

    private String cpfCre;
    private String valor;
    private Conta contaCre;
    private Conta contaDev;
    private final ContaService contaService = new ContaService();
    private final HistMovimentacaoService serHtm = new HistMovimentacaoService();
    private final TipoMovimentacaoService serTpm = new TipoMovimentacaoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entre_contas_valor);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        cpfCre = getIntent().getStringExtra("cpfTransferencia");

        Conta conta = contaService.getCPF(cpfCre);
        Cliente cliente = conta.getCliente();

        contaDev = contaService.getCPF(LoginActivity.cpf);
        contaCre = contaService.getCPF(cpfCre);

        TextView nomeCliente = findViewById(R.id.nomeClienteText);
        nomeCliente.setText(cliente.getNome());
        TextView contaCliente = findViewById(R.id.contaClienteText);
        contaCliente.setText("Numero: " + conta.getNumero() + " | "+ "Agencia: " + conta.getAgencia());


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

                EditText edtValor = findViewById(R.id.editTextVALORTRANSFERENCIA);
                valor = edtValor.getText().toString();
                if (valor.equals("") || valor.equals("0")){
                    Toast.makeText(EntreContasValorActivity.this, "Insira uma quantidade valida", Toast.LENGTH_SHORT).show();
                } else {
                    realizarTransferencia();
                }
            }
        });
    }

    public void openTransferenciaContas() {
        Intent intent = new Intent(this, EntreContasCPFActivity.class);
        startActivity(intent);
    }

    public void openComprovanteTransferencia() {
        Intent intent = new Intent(this, ComprovanteTransferenciaActivity.class);
        intent.putExtra("cpfTransferencia", cpfCre);
        intent.putExtra("valorTransferencia", valor);
        startActivity(intent);
    }

    private String inserirData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormat.format(date);
    }

    private void realizarTransferencia(){
        String confirm = serHtm.adicionar(movimentacaoCre());
        String confirm2 = serHtm.adicionar(movimentacaoDev());

        if (confirm.equals("exito") && confirm2.equals("exito")){
            Toast.makeText(this, "Transferencia Realizado com sucesso: " + confirm, Toast.LENGTH_LONG).show();
            int saldoDev = (int) (contaDev.getSaldo() - Double.parseDouble(valor));
            int saldoCre = (int) (contaCre.getSaldo() + Double.parseDouble(valor));

            contaService.atualizarSaldo(contaDev.getId(), saldoDev);
            contaService.atualizarSaldo(contaCre.getId(), saldoCre);

            openComprovanteTransferencia();
        }else {
            Toast.makeText(this, "Houve erro ao realizar a Transferencia: " + confirm, Toast.LENGTH_SHORT).show();
        }
    }

    private HistMovimentacao movimentacaoCre (){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = serTpm.getChave("REALTRANSFE");

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setDescricao("Transferencia");
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(contaCre);
        movimentacao.setIdContaTransferencia(contaDev.getId());
        movimentacao.setDataInclusao(inserirData());

        return movimentacao;
    }

    private HistMovimentacao movimentacaoDev(){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = serTpm.getChave("RECETRANSFE");

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setDescricao("Transferencia");
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(contaDev);
        movimentacao.setIdContaTransferencia(contaCre.getId());
        movimentacao.setDataInclusao(inserirData());

        return movimentacao;
    }
}