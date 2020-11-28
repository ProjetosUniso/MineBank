package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.HistMovimentacaoService;

import java.util.List;

public class ExtratoActivity extends Activity {

    private final HistMovimentacaoService histMovimentacaoService = new HistMovimentacaoService();
    private final ContaService contaService = new ContaService() ;
    private List<HistMovimentacao> listHistorico;

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        Conta conta = contaService.getCPF(LoginActivity.cpf);

        listHistorico = histMovimentacaoService.getID( conta.getId() );

        if ( listHistorico != null ){
            loadExtrato();
        }else{
            Toast.makeText(ExtratoActivity.this, "Você ainda não efetuou nenhuma trasnferencia", Toast.LENGTH_LONG).show();
        }
    }

    private void loadExtrato() {

        TableLayout tl = findViewById(R.id.extratoTable);

        for(int i=listHistorico.size(); i >= 1 ; i--) {

            HistMovimentacao historico = listHistorico.get(i-1);

            TableRow tr = new TableRow(this);

            for(int j=0; j<3; j++) {
                TextView tv = new TextView(this);


                tv.setGravity(Gravity.CENTER);
                tv.setPadding(5, 5,5,5);

                if(j==0) { tv.setText( returnData(historico.getDataInclusao()) ); }
                else if (j==1) { tv.setText( historico.getDescricao() ); }
                else { tv.setText( (String.valueOf( historico.getValor() )) ); }

                tr.addView(tv);
            }


            tr.setBackgroundColor(Color.parseColor("#e8e8e8"));
            tr.setPadding(5,5,5,5);
            tl.addView(tr);
        }

    }

    public void openMenuActivity(View view) {
        mediaPlayer.start();

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private String returnData(String data){
        String[] strData = data.split("T");
        String[] str = strData[0].split("-");
        StringBuilder n = new StringBuilder(str[2]);

        for (int i = 1; i >= 0; i--){
            n.append("-").append(str[i]);
        }

        return n.toString();
    }
}