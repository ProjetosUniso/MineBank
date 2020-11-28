package com.projetosuniso.digdin;

import android.net.Uri;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.service.ContaService;

public class ComprovanteTransferenciaActivity extends Activity {

    private String cpfCre;
    private String valor;
    private final ContaService contaService = new ContaService();

    private Conta contaDev = null;
    private Conta contaCre = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_transferencia);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        cpfCre = getIntent().getStringExtra("cpfTransferencia");
        valor = getIntent().getStringExtra("valorTransferencia");

        contaDev = contaService.getCPF(LoginActivity.cpf);
        contaCre = contaService.getCPF(cpfCre);

        atualizarPlaca();

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openMenu();
            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void atualizarPlaca(){

        Cliente clienteDev = contaDev.getCliente();
        Cliente clienteCre = contaCre.getCliente();

        TextView nomeClienteDev = findViewById(R.id.nomeDevedorText);
        nomeClienteDev.setText(clienteDev.getNome());
        TextView contaClienteDev = findViewById(R.id.contaDevedorText);
        contaClienteDev.setText("Numero: " + contaDev.getNumero() + " | "+ "Agencia: " + contaDev.getAgencia());

        TextView nomeClienteCre = findViewById(R.id.nomeCredorText);
        nomeClienteCre.setText(clienteCre.getNome());
        TextView contaClienteCre = findViewById(R.id.contaCredorText);
        contaClienteCre.setText("Numero: " + contaCre.getNumero() + " | "+ "Agencia: " + contaCre.getAgencia());

        TextView valorTrs = findViewById(R.id.valorRecebidoText);
        valorTrs.setText(valor);
    }

    public void enviarComprovante(View view) {

        Cliente clienteDev = contaDev.getCliente();
        Cliente clienteCre = contaCre.getCliente();

        String nomeDev = clienteDev.getNome();
        String numeroaDev = String.valueOf(contaDev.getNumero());
        String agenciaDev = String.valueOf(contaDev.getAgencia());

        String nomeCre = clienteCre.getNome();
        String numeroCre = String.valueOf(contaCre.getNumero());
        String agenciaCre = String.valueOf(contaCre.getAgencia());

        TextView valorTrs = findViewById(R.id.valorRecebidoText);
        String valor = String.valueOf(valorTrs.getText());

        String mensagem = String.format("COMPROVANTE DE PAGAMENTO\n" +
                                        "Pagamento realizado por: %s" +
                                        "Conta: %s Agência: %s\nValor: %s" +
                                        "Tranferência para: %s\n" +
                                        "Conta: %s Agência: %s\n" +
                                        "Comprovado pela equipe MineBank",
                                        nomeDev, numeroaDev, agenciaDev, valor, nomeCre, numeroCre, agenciaCre);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        startActivity(intent);
    }
}