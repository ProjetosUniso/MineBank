package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Endereco;
import com.projetosuniso.digdin.service.EnderecoService;
import com.projetosuniso.digdin.utils.MaskEditUtil;

public class CadastroEnderecoActivity extends Activity {

    private final Cliente EXTRA_CLIENTE = new Cliente();
    private Cliente cliente;

    private EnderecoService enderecoService = new EnderecoService();

    String CEP;
    String Numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        final EditText editTextCEP = findViewById(R.id.editTextCEP);
        final EditText editTextEndereco = findViewById(R.id.editTextENDERECO);
        final EditText editTextCidade = findViewById(R.id.editTextCIDADE);
        final EditText editTextEstado = findViewById(R.id.editTextESTADO);
        final EditText editTextNumero = findViewById(R.id.editTextNUMERO);

        final TextView textViewInvalido = findViewById(R.id.textCEPInvalido);
        final TextView textViewNumeroInvalido = findViewById(R.id.textNumeroInvalido);

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

        editTextCEP.addTextChangedListener(MaskEditUtil.mask(editTextCEP, MaskEditUtil.FORMAT_CEP));
        editTextCEP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                CEP = editTextCEP.getText().toString();

                if(!hasFocus) {
                    CEP = MaskEditUtil.unmask(CEP);
                    // passar edittext cep
                    if (!CEP.matches("")) {
                        Endereco endereco = enderecoService.getCEP(CEP);

                        if(endereco != null) {
                            editTextEndereco.setText(endereco.getRua());
                            editTextCidade.setText(endereco.getCidade());
                            editTextEstado.setText(endereco.getUf());
                        }
                        else {
                            editTextEndereco.setText("");
                            editTextCidade.setText("");
                            editTextEstado.setText("");

                            editTextCEP.setBackgroundResource(R.drawable.edittext_border);
                            textViewInvalido.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        editTextEndereco.setText("");
                        editTextCidade.setText("");
                        editTextEstado.setText("");
                    }
                }
                else {
                    editTextCEP.setBackgroundResource(R.drawable.edittext_default);
                    textViewInvalido.setVisibility(View.INVISIBLE);
                }
            }
        });
        editTextCEP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                CEP = editTextCEP.getText().toString();

                if(CEP.length() == 9) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextCEP.getWindowToken(), 0);
                    editTextCEP.clearFocus();
                }
            }
        });

        editTextNumero.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    textViewNumeroInvalido.setVisibility(View.INVISIBLE);
                    editTextNumero.setBackgroundResource(R.drawable.edittext_default);
                }
            }
        });
    }

    public void openCadastroInfoPessoal() {
        Intent intent = new Intent(this, CadastroInfoPessoalActivity.class);
        startActivity((intent));
    }

    public void openCadastroSenha() {
        EditText editTextNumero = findViewById(R.id.editTextNUMERO);
        TextView textViewNumeroInvalido = findViewById(R.id.textNumeroInvalido);

        Numero = editTextNumero.getText().toString();

        if(!Numero.matches("")) {
            Intent intent = new Intent(this, CadastroSenhaActivity.class);
            startActivity((intent));
        }
        else {
            textViewNumeroInvalido.setVisibility(View.VISIBLE);
            editTextNumero.setBackgroundResource(R.drawable.edittext_border);
        }

    }
}