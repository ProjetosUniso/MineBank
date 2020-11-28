package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.HistMovimentacaoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtratoActivity extends Activity {

    private final HistMovimentacaoService histMovimentacaoService = new HistMovimentacaoService();
    private final ContaService contaService = new ContaService() ;
    private List<HistMovimentacao> listHistorico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);


        Conta conta = contaService.getCPF(LoginActivity.cpf);

        listHistorico = histMovimentacaoService.getID(conta.getId());

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
            }
        });

        loadExtrato();
    }

    private void loadExtrato() {

        TableLayout tl = findViewById(R.id.extratoTable);

        for(int i=0; i < listHistorico.size(); i++) {

            HistMovimentacao historico = listHistorico.get(i);

            TableRow tr = new TableRow(this);

            for(int j=0; j<3; j++) {
                TextView tv = new TextView(this);


                tv.setGravity(Gravity.CENTER);
                tv.setPadding(5, 5,5,5);

                if(j==0) { tv.setText( returnData(historico.getDataInclusao()) ); }
                else if (j==1) { tv.setText( historico.getDescricao() ); }
                else if (j==2) { tv.setText( (String.valueOf( historico.getValor() )) ); }

                tr.addView(tv);
            }


            tr.setBackgroundColor(Color.parseColor("#e8e8e8"));
            tr.setPadding(5,5,5,5);
            tl.addView(tr);
        }

    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private String returnData(String data){
        String[] novaData = data.split("T");
        return novaData[0];
    }
}