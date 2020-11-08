package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

    int auxSaldo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView saldoText = findViewById(R.id.saldo);
        saldoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaldo();
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        Button transferenciaButton = findViewById(R.id.transferenciaButton);
        transferenciaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransferencia();
            }
        });
    }

    public void showSaldo() {
        TextView saldoText = findViewById(R.id.saldo);
        if(auxSaldo == 0) {

            saldoText.setText("786,40");
            auxSaldo = 1;
        }
        else {
            saldoText.setText(R.string.saldo);
            auxSaldo = 0;
        }

    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openTransferencia() {
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }

}