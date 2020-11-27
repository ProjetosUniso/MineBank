package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PagamentosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        final EditText editTextNumPagamento = findViewById(R.id.editTextNUMPAGAMENTO);

        final LinearLayout pagamentoInfo = findViewById(R.id.pagamentoInfo);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
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

                if (pagamento.equals("000000")) {
                    pagamentoInfo.setVisibility(View.VISIBLE);
                }
                else {
                    pagamentoInfo.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}