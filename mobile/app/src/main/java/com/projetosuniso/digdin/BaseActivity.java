package com.projetosuniso.digdin;

import android.app.Activity;
import android.os.Bundle;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.HistMovimentacaoService;
import com.projetosuniso.digdin.service.TipoMovimentacaoService;


public class BaseActivity extends Activity {

    HistMovimentacaoService service;
    ContaService serco;
    TipoMovimentacaoService sertpm;
    HistMovimentacao teste = new HistMovimentacao();
    TipoMovimentacao tpm = new TipoMovimentacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        service = new HistMovimentacaoService();
        sertpm = new TipoMovimentacaoService();
        serco = new ContaService();

        tpm.setId(5);
        tpm.setDescricao("resgate poupança");
        tpm.setChave("RESGPOUPANCA");



        teste.setConta(serco.getID(4));
        teste.setMovimentacao(tpm);
        teste.setIdContaTransferencia(4);
        teste.setDataInclusao("2020-11-12T00:00:00.000+00:00");
        teste.setDescricao("resgate");


        String n = service.adicionar(teste);

        /*teste.setCpf("69617230042");

        boolean n  = service.atualizar( teste, 4 );

        Cliente resul = service.getID(4);*/

    }
}