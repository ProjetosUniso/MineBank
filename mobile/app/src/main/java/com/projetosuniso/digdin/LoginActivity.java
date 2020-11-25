package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;
import com.projetosuniso.digdin.service.EnderecoService;
import com.projetosuniso.digdin.utils.MaskEditUtil;

public class LoginActivity extends Activity {

    public static String cpf;

    private final ContaService contaService = new ContaService();

    private EnderecoService enderecoService = new EnderecoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Button entrarButton = findViewById(R.id.entrarButton);
        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
            }
        });

        Button cadastrarButton = findViewById(R.id.cadastrarButton);
        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroEmail();
            }
        });

        EditText editTextCPF = findViewById(R.id.editTextCPF);
        editTextCPF.addTextChangedListener(MaskEditUtil.mask(editTextCPF, MaskEditUtil.FORMAT_CPF));
    }

    public void openMenu() {
        //Chama o metodo Login para fazer a validação do cpf e senha indicado pelo cliente
        EditText editTextCPF = findViewById(R.id.editTextCPF);
        EditText editTextSenha = findViewById(R.id.editTextSENHA);

        cpf = MaskEditUtil.unmask(editTextCPF.getText().toString());
        String senha = String.valueOf(editTextSenha.getText());

        cpf = "48960393860";
        senha = "123456";

        boolean loginExiste = contaService.login(senha,cpf);

        if (loginExiste){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    public void openCadastroEmail() {
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity((intent));
    }
}