package com.projetosuniso.digdin;

import android.widget.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.projetosuniso.digdin.model.Boleto;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.HistMovimentacaoService;
import com.projetosuniso.digdin.service.TipoMovimentacaoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PagamentosActivity extends Activity {

    private final ContaService contaService = new ContaService();
    private final HistMovimentacaoService serHtm = new HistMovimentacaoService();
    private final TipoMovimentacaoService serTpm = new TipoMovimentacaoService();
    private Conta conta = new Conta();
    private String valor;

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos);

        conta = contaService.getCPF(LoginActivity.cpf);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        final EditText editTextNumPagamento = findViewById(R.id.editTextNUMPAGAMENTO);

        final TextView textViewSaldo = findViewById(R.id.saldo);
        final TextView textViewBoletoNEncontrado = findViewById(R.id.textNumeroNaoEncontrado);
        final TextView textViewValor = findViewById(R.id.valorPagamentoText);
        final TextView textViewDescricao = findViewById(R.id.descricaoText);

        final LinearLayout pagamentoInfo = findViewById(R.id.pagamentoInfo);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        textViewSaldo.setText(String.valueOf( conta.getSaldo() ));

        editTextNumPagamento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    int pagamento = Integer.parseInt(editTextNumPagamento.getText().toString());
                    int id = retornaBoleto(pagamento);

                    if (id == 9) {
                        pagamentoInfo.setVisibility(View.INVISIBLE);
                        textViewBoletoNEncontrado.setVisibility(View.VISIBLE);
                    }
                    else {
                        Boleto boleto = Boleto.boletos[id];

                        valor = boleto.getValor().toString();
                        textViewValor.setText(valor);
                        textViewDescricao.setText(boleto.getDescricao());
                        pagamentoInfo.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    pagamentoInfo.setVisibility(View.INVISIBLE);
                    textViewBoletoNEncontrado.setVisibility(View.INVISIBLE);
                }
            }
        });

        editTextNumPagamento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pagamento = editTextNumPagamento.getText().toString();

                if(pagamento.length() == 6) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextNumPagamento.getWindowToken(), 0);
                    editTextNumPagamento.clearFocus();
                }

            }
        });
        Button pagarButton = findViewById(R.id.pagarButton);
        pagarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movimentacao();
                openMenu();
            }
        });
    }

    public void openMenu() {
        mediaPlayer.start();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private int retornaBoleto(int id) {
        switch (id) {
            case 101010: return 0;
            case 736001: return 1;
            case 400289: return 2;
            case 847566: return 3;
            default: return 9;
        }
    }

    private String inserirData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    private void movimentacao (){
        HistMovimentacao movimentacao = new HistMovimentacao();
        TipoMovimentacao tpMoviment = serTpm.getChave("PAGBOLETO");

        movimentacao.setValor(Double.parseDouble(valor));
        movimentacao.setDescricao("Pagamento - Boleto");
        movimentacao.setMovimentacao(tpMoviment);
        movimentacao.setConta(conta);
        movimentacao.setIdContaTransferencia(conta.getId());
        movimentacao.setDataInclusao(inserirData());

        String confirm = serHtm.adicionar(movimentacao);

        if (confirm.equals("exito")){
            int saldo = (int) ( conta.getSaldo() - Integer.parseInt(valor) );
            contaService.atualizarSaldo(conta.getId(), saldo);
            Toast.makeText(this, "Pagamento realizado com sucesso", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Houve erro ao Realizar o pagamento: " + confirm, Toast.LENGTH_SHORT).show();
        }
    }
}