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

    private String valor;
    private final ContaService contaService = new ContaService();

    private MediaPlayer mediaPlayer = null;

    private Conta contaDev = null;
    private Conta contaCre = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_transferencia);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        String cpfCre = getIntent().getStringExtra("cpfTransferencia");
        valor = getIntent().getStringExtra("valorTransferencia");

        contaDev = contaService.getCPF(LoginActivity.cpf);
        contaCre = contaService.getCPF(cpfCre);

        atualizarPlaca();
    }

    public void openMenuActivity(View view) {
        mediaPlayer.start();

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void atualizarPlaca() {

        Cliente clienteDev = contaDev.getCliente();
        Cliente clienteCre = contaCre.getCliente();

        TextView nomeClienteDev = findViewById(R.id.nomeDevedorText);
        nomeClienteDev.setText(clienteDev.getNome());
        TextView contaClienteDev = findViewById(R.id.contaDevedorText);
        String placaDev = String.format("Numero: %s | Agencia: %s ", contaDev.getNumero(), contaDev.getAgencia());
        contaClienteDev.setText(placaDev);

        TextView nomeClienteCre = findViewById(R.id.nomeCredorText);
        nomeClienteCre.setText(clienteCre.getNome());
        TextView contaClienteCre = findViewById(R.id.contaCredorText);
        String placaCre = String.format("Numero: %s | Agencia: %s ", contaCre.getNumero(), contaCre.getAgencia());
        contaClienteCre.setText(placaCre);

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
                                        "Pagamento realizado por: %s\n" +
                                        "Conta: %s\nAgência: %s\nValor: %s esmeraldas.\n" +
                                        "Tranferência para: %s\n" +
                                        "Conta: %s\nAgência: %s\n" +
                                        "Comprovado pela equipe MineBank.",
                                        nomeDev, numeroaDev, agenciaDev, valor, nomeCre, numeroCre, agenciaCre);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        startActivity(intent);
    }
}