package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Endereco;
import com.projetosuniso.digdin.service.EnderecoService;
import com.projetosuniso.digdin.utils.MaskEditUtil;

public class CadastroEnderecoActivity extends Activity {

    private final Cliente EXTRA_CLIENTE = new Cliente();
    private Cliente cliente;

    private EnderecoService enderecoService = new EnderecoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        // passar edittext cep
        Endereco endereco = enderecoService.getCEP("18116010");

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroInfoPessoal();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroSenha();
            }
        });

        EditText editTextCEP = findViewById(R.id.editTextCEP);
        editTextCEP.addTextChangedListener(MaskEditUtil.mask(editTextCEP, MaskEditUtil.FORMAT_CEP));
    }

    public void openCadastroInfoPessoal() {
        Intent intent = new Intent(this, CadastroInfoPessoalActivity.class);
        startActivity((intent));
    }

    public void openCadastroSenha() {
        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        startActivity((intent));
    }
}