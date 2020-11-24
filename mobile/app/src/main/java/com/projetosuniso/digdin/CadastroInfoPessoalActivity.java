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
import android.widget.ImageView;
import android.widget.TextView;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.utils.CpfValidatorUtil;
import com.projetosuniso.digdin.utils.DataValidatorUtil;
import com.projetosuniso.digdin.utils.MaskEditUtil;

public class CadastroInfoPessoalActivity extends Activity {

    private final Cliente EXTRA_CLIENTE = new Cliente();
    private Cliente cliente;

    boolean CPFvalid = true;
    boolean CPFpreenchido = false;
    boolean NOMEvalid = false;
    boolean SOBRENOMEvalid = false;
    boolean RGvalid = false;
    boolean DATAvalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_info_pessoal);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        final ImageView checkCPF = findViewById(R.id.imageCheckCPF);
        final ImageView checkRG = findViewById(R.id.imageCheckRG);
        final ImageView checkDATA = findViewById(R.id.imageCheckDATA);
        final ImageView wrongCPF = findViewById(R.id.imageWrongCPF);
        final ImageView wrongRG = findViewById(R.id.imageWrongRG);
        final ImageView wrongDATA = findViewById(R.id.imageWrongDATA);

        final EditText editTextNome = findViewById(R.id.editTextNOME);
        final EditText editTextSobrenome = findViewById(R.id.editTextSOBRENOME);
        final EditText editTextCPF = findViewById(R.id.editTextCPF);
        final EditText editTextRG = findViewById(R.id.editTextRG);
        final EditText editTextDATA = findViewById(R.id.editTextNASCIMENTO);

        final TextView textPreencher = findViewById(R.id.textPreencherCampos);
        final TextView CPFInvalido = findViewById(R.id.textCPFInvalido);
        final TextView DATAInvalida = findViewById(R.id.textDATAInvalida);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroEmail();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroEndereco();
            }
        });

        editTextNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String NOME = editTextNome.getText().toString();

                if(!hasFocus) {
                    if(NOME.matches("")) {
                        NOMEvalid = false;
                    }
                    else {
                        NOMEvalid = true;
                    }
                }
                else if(hasFocus) {
                }
            }
        });

        editTextSobrenome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String SOBRENOME = editTextSobrenome.getText().toString();

                if(!hasFocus) {
                    if(SOBRENOME.matches("")) {
                        SOBRENOMEvalid = false;
                    }
                    else {
                        SOBRENOMEvalid = true;
                    }
                }
                else if(hasFocus) {
                }
            }
        });


        editTextCPF.addTextChangedListener(MaskEditUtil.mask(editTextCPF, MaskEditUtil.FORMAT_CPF));
        editTextCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {



                if(!hasFocus) {
                    String CPF = editTextCPF.getText().toString();
                    String unmaskedCPF = MaskEditUtil.unmask(CPF);

                    if(CPF.matches("")) {
                        CPFpreenchido = false;
                        editTextCPF.setBackgroundResource(R.drawable.edittext_border);
                        checkCPF.setVisibility(View.INVISIBLE);
                        wrongCPF.setVisibility(View.VISIBLE);
                    }
                    else {

                        if(CpfValidatorUtil.isCPF(unmaskedCPF) == false) {
                            editTextCPF.setBackgroundResource(R.drawable.edittext_default);
                            textPreencher.setVisibility(View.INVISIBLE);
                            editTextCPF.setBackgroundResource(R.drawable.edittext_border);
                            checkCPF.setVisibility(View.INVISIBLE);
                            wrongCPF.setVisibility(View.VISIBLE);
                            CPFInvalido.setVisibility(View.VISIBLE);
                            CPFvalid = false;
                        }
                        else {
                            CPFvalid = true;
                            CPFpreenchido = true;
                            checkCPF.setVisibility(View.VISIBLE);
                        }
                    }

                }
                else if(hasFocus) {
                    CPFvalid = true;
                    editTextCPF.setBackgroundResource(R.drawable.edittext_default);
                    CPFInvalido.setVisibility(View.INVISIBLE);
                    wrongCPF.setVisibility(View.INVISIBLE);
                }


            }
        });
        editTextCPF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String CPF = editTextCPF.getText().toString();

                if(CPF.length() == 14) {
                    //EditText editTextRG = findViewById(R.id.editTextRG);
                    editTextCPF.clearFocus();
                    editTextRG.requestFocus();
                }

            }
        });

        editTextRG.addTextChangedListener(MaskEditUtil.mask(editTextRG, MaskEditUtil.FORMAT_RG));
        editTextRG.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    String RG = editTextRG.getText().toString();

                    if(RG.matches("") || RG.length() < 12) {
                        editTextRG.setBackgroundResource(R.drawable.edittext_border);
                        checkRG.setVisibility(View.INVISIBLE);
                        wrongRG.setVisibility(View.VISIBLE);
                        RGvalid = false;
                    }
                    else {
                        checkRG.setVisibility(View.VISIBLE);
                        RGvalid = true;
                    }
                }
                else if(hasFocus) {
                    wrongRG.setVisibility(View.INVISIBLE);
                    editTextRG.setBackgroundResource(R.drawable.edittext_default);
                }
            }
        });
        editTextRG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                String RG = editTextRG.getText().toString();

                if(RG.length() == 12) {
                    editTextRG.clearFocus();
                    editTextDATA.requestFocus();
                }

            }
        });

        editTextDATA.addTextChangedListener(MaskEditUtil.mask(editTextDATA, MaskEditUtil.FORMAT_DATE));
        editTextDATA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(!hasFocus) {
                    String DATA = editTextDATA.getText().toString();

                    if(DATA.matches("") || DATA.length()  < 10) {
                        checkDATA.setVisibility(View.INVISIBLE);
                        wrongDATA.setVisibility(View.VISIBLE);
                        editTextDATA.setBackgroundResource(R.drawable.edittext_border);
                        DATAvalid = false;
                    }
                    else {
                        if(DataValidatorUtil.isData(DATA)) {
                            checkDATA.setVisibility(View.VISIBLE);
                            DATAvalid = true;
                        }
                        else {
                            checkDATA.setVisibility(View.INVISIBLE);
                            wrongDATA.setVisibility(View.VISIBLE);
                            editTextDATA.setBackgroundResource(R.drawable.edittext_border);
                            DATAInvalida.setVisibility(View.VISIBLE);
                            DATAvalid = false;
                        }

                    }
                }
                else if(hasFocus) {
                    wrongDATA.setVisibility(View.INVISIBLE);
                    editTextDATA.setBackgroundResource(R.drawable.edittext_default);
                    DATAInvalida.setVisibility(View.INVISIBLE);
                }
            }
        });
        editTextDATA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String DATA = editTextDATA.getText().toString();

                if(DATA.length() == 10) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextDATA.getWindowToken(), 0);
                    editTextDATA.clearFocus();
                }

            }
        });
    }


    public void openCadastroEmail() {
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity(intent);
    }

    public void openCadastroEndereco() {

        if(CPFvalid) {
            if (NOMEvalid && SOBRENOMEvalid && CPFpreenchido && RGvalid && DATAvalid ) {


                EditText editTextNome = findViewById(R.id.editTextNOME);
                EditText editTextSobrenome = findViewById(R.id.editTextSOBRENOME);
                EditText editTextCPF = findViewById(R.id.editTextCPF);
                EditText editTextRG = findViewById(R.id.editTextRG);
                EditText editTextDATA = findViewById(R.id.editTextNASCIMENTO);

                String nome = editTextNome.getText().toString();
                String sobrenome = editTextSobrenome.getText().toString();
                String cpf = editTextCPF.getText().toString();
                String rg = editTextRG.getText().toString();
                String data = editTextDATA.getText().toString();

                cliente.setNome(nome);
                cliente.setSobrenome(sobrenome);
                cliente.setCpf(cpf);
                cliente.setRg(rg);
                cliente.setDataNascimento(data);

                Intent intent = new Intent(this, CadastroEnderecoActivity.class);
                intent.putExtra("cliente", cliente);

                startActivity((intent));
            } else {
                TextView textPreencher = findViewById(R.id.textPreencherCampos);
                textPreencher.setVisibility(View.VISIBLE);
            }
        }

    }
}