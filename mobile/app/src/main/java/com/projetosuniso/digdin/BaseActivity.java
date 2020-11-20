package com.projetosuniso.digdin;

import android.app.Activity;
import android.os.Bundle;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.service.ClienteService;


public class BaseActivity extends Activity {

    ClienteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        service = new ClienteService();

        Cliente teste = service.getID(4);

        teste.setCpf("69617230042");

        boolean n  = service.atualizar( teste, 4 );

        Cliente resul = service.getID(4);

    }
}