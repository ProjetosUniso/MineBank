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

import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.EnderecoService;
import com.projetosuniso.digdin.utils.MaskEditUtil;

public class LoginActivity extends Activity {

    public static String cpf;
    private final ContaService contaService = new ContaService();

    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        final EditText editTextSenha = findViewById(R.id.editTextSENHA);

        final EditText editTextCPF = findViewById(R.id.editTextCPF);

        final TextView textViewInvalido = findViewById(R.id.textLoginInvalido);

        editTextCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    textViewInvalido.setVisibility(View.INVISIBLE);
                }
            }
        });

        editTextCPF.addTextChangedListener(MaskEditUtil.mask(editTextCPF, MaskEditUtil.FORMAT_CPF));
        editTextCPF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String cpfLenght = editTextCPF.getText().toString();

                if (cpfLenght.length() == 14) {
                    editTextCPF.clearFocus();
                    editTextSenha.requestFocus();
                }
            }
        });

        editTextSenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    textViewInvalido.setVisibility(View.INVISIBLE);
                }
            }
        });

        editTextSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String senha = editTextSenha.getText().toString();

                if(senha.length() == 6) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextSenha.getWindowToken(), 0);
                    editTextSenha.clearFocus();
                }

            }
        });
    }

    /*Chama o metodo Login para fazer a validação do cpf e senha indicado pelo cliente*/
    public void openMenu(View view) {
        mediaPlayer.start();
        EditText editTextCPF = findViewById(R.id.editTextCPF);
        EditText editTextSenha = findViewById(R.id.editTextSENHA);

        cpf = MaskEditUtil.unmask(editTextCPF.getText().toString());
        String senha = String.valueOf(editTextSenha.getText());

        boolean loginExiste = contaService.login(senha,cpf);

        if (loginExiste) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        else {
            TextView textViewInvalido = findViewById(R.id.textLoginInvalido);

            textViewInvalido.setVisibility(View.VISIBLE);
        }
    }

    public void openCadastroEmail(View view) {
        mediaPlayer.start();
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity((intent));
    }
}