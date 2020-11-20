package com.projetosuniso.digdin;

import android.app.Activity;
import android.os.Bundle;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.requisicoes.endereco.EnderecoAtualizar;
import com.projetosuniso.digdin.service.ClienteService;


public class BaseActivity extends Activity {

    ClienteService service;
    EnderecoAtualizar atualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        service = new ClienteService();

        Cliente c =service.getID(4);

        boolean teste = service.verificarCPF("6961723006");

      /*  teste.setNumero(140);

        String end = service.atualizar(4, teste );

        Endereco resul = service.getID(4);*/



    }
}