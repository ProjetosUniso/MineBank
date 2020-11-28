package com.projetosuniso.digdin;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ClienteService;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.utils.CpfValidatorUtil;
import com.projetosuniso.digdin.utils.MaskEditUtil;

import org.w3c.dom.Text;

public class EntreContasCPFActivity extends Activity {

    boolean CPFvalid = false;
    private EditText editTextCPF;
    private ClienteService clienteService = new ClienteService();
    private ContaService contaService = new ContaService();
    private String unmaskedCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entre_contas);

        final TextView CPFInvalido = findViewById(R.id.textCPFNaoEncontrado);
        final LinearLayout clienteInfo = findViewById(R.id.clientInfo);

        editTextCPF = findViewById(R.id.editTextCPFTRANSFERENCIA);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openTransferencia();
            }
        });

        Button transferirParaButton = findViewById(R.id.transferirParaButton);
        transferirParaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openEntreContasValor();
            }
        });


        editTextCPF.addTextChangedListener(MaskEditUtil.mask(editTextCPF, MaskEditUtil.FORMAT_CPF));
        editTextCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(!hasFocus) {
                    String CPF = editTextCPF.getText().toString();
                    unmaskedCPF = MaskEditUtil.unmask(CPF);

                    if(CPF.matches("")) {
                        editTextCPF.setBackgroundResource(R.drawable.edittext_border);
                    }
                    else {

                        if(!CpfValidatorUtil.isCPF(unmaskedCPF)) {
                            CPFvalid = false;
                            editTextCPF.setBackgroundResource(R.drawable.edittext_default);
                            editTextCPF.setBackgroundResource(R.drawable.edittext_border);
                            CPFInvalido.setVisibility(View.VISIBLE);
                            clienteInfo.setVisibility(View.INVISIBLE);
                        }
                        else {
                            CPFvalid = clienteService.verificarCPF(unmaskedCPF);
                        }
                    }

                }
                else {
                    CPFvalid = false;
                    editTextCPF.setBackgroundResource(R.drawable.edittext_default);
                    CPFInvalido.setVisibility(View.INVISIBLE);
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
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextCPF.getWindowToken(), 0);
                    editTextCPF.clearFocus();
                }

                if(CPFvalid) {
                    Conta conta = contaService.getCPF(unmaskedCPF);
                    Cliente cliente = conta.getCliente();

                    TextView nomeCliente = findViewById(R.id.nomeClienteText);
                    nomeCliente.setText(cliente.getNome());
                    TextView contaCliente = findViewById(R.id.contaClienteText);

                    String descricao = String.format("Agência: %s | Numero: %s",  conta.getAgencia(), conta.getNumero());

                    contaCliente.setText(descricao);

                    clienteInfo.setVisibility(View.VISIBLE);
                }
                else {
                    clienteInfo.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    public void openTransferencia() {
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }

    public void openEntreContasValor() {
        Intent intent = new Intent(this, EntreContasValorActivity.class);
        intent.putExtra("cpfTransferencia", unmaskedCPF);
        startActivity(intent);
    }
}