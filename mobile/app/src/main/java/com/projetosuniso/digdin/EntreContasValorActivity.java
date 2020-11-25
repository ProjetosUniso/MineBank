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

import java.text.SimpleDateFormat;
import java.util.Date;

public class EntreContasValorActivity extends Activity {

    private String cpfRec;
    private String valor;
    private final ContaService contaService = new ContaService();
    private final HistMovimentacaoService serHtm = new HistMovimentacaoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entre_contas_valor);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        cpfRec = getIntent().getStringExtra("cpfTransferencia");

        Conta conta = contaService.getCPF(cpfRec);
        Cliente cliente = conta.getCliente();

        TextView nomeCliente = findViewById(R.id.nomeClienteText);
        nomeCliente.setText(cliente.getNome());
        TextView contaCliente = findViewById(R.id.contaClienteText);
        contaCliente.setText("Conta: " + conta.getAgencia() + " | "+ "Agencia: " + conta.getAgencia());



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
                realizarTransferencia();
            }
        });
    }

    public void openTransferenciaContas() {
        Intent intent = new Intent(this, EntreContasCPFActivity.class);
        startActivity(intent);
    }

    public void openComprovanteTransferencia() {
        Intent intent = new Intent(this, ComprovanteTransferenciaActivity.class);
        intent.putExtra("cpfTransferencia", cpfRec);
        intent.putExtra("valorTransferencia", valor);
        startActivity(intent);
    }

    private String inserirData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormat.format(date);
    }

    private void realizarTransferencia(){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = new TipoMovimentacao();

        Conta contaPag = contaService.getCPF(LoginActivity.cpf);
        Conta contaRec = contaService.getCPF(cpfRec);

        EditText edtValor = findViewById(R.id.editTextVALORTRANSFERENCIA);
        valor = edtValor.getText().toString();

        //tpMoviment = serTpm.getID();
        tpMoviment.setChave("REALTRANSFERENCIA");
        tpMoviment.setDescricao("Realiza Transferencia");
        tpMoviment.setId(3);

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setDescricao("transferencia");
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(contaPag);
        movimentacao.setIdContaTransferencia(contaRec.getId());
        movimentacao.setDataInclusao(inserirData());

        String confirm = serHtm.adicionar(movimentacao);

        if (confirm.equals("exito")){
            Toast.makeText(this, "Transferencia Realizado com sucesso: " + confirm, Toast.LENGTH_LONG).show();
            contaPag.setSaldo(contaPag.getSaldo() + Double.parseDouble(valor));

            contaService.atualizar(contaPag, contaPag.getId());

            openComprovanteTransferencia();
        }else {
            Toast.makeText(this, "Houve erro ao realizar a Transferencia: " + confirm, Toast.LENGTH_SHORT).show();
        }
    }
}