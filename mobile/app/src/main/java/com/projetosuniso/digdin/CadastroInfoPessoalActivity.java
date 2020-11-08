package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projetosuniso.digdin.utils.MaskEditUtil;

public class CadastroInfoPessoalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_info_pessoal);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroEmail();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroEndereco();
            }
        });

        EditText editTextCPF = findViewById(R.id.editTextCPF);
        editTextCPF.addTextChangedListener(MaskEditUtil.mask(editTextCPF, MaskEditUtil.FORMAT_CPF));

        EditText editTextRG = findViewById(R.id.editTextRG);
        editTextRG.addTextChangedListener(MaskEditUtil.mask(editTextRG, MaskEditUtil.FORMAT_RG));

        EditText editTextDATA = findViewById(R.id.editTextNASCIMENTO);
        editTextDATA.addTextChangedListener(MaskEditUtil.mask(editTextDATA, MaskEditUtil.FORMAT_DATE));
    }

    public void openCadastroEmail() {
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity(intent);
    }

    public void openCadastroEndereco() {
        Intent intent = new Intent(this, CadastroEnederecoActivity.class);
        startActivity((intent));
    }
}