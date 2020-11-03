package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.projetosuniso.digdin.controller.LoginCtrl;

public class LoginActivity extends Activity {

    private final LoginCtrl loginCtrl = new LoginCtrl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.entrarButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }

    public void openMenu() {

        //Chama o metodo Login para fazer a validação do cpf indicado pelo cliente
        if (loginCtrl.Login("123123125423")){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

    }
}