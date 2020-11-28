package com.projetosuniso.digdin.utils.Email;

import android.content.Context;

public class JavaEmailService {

    public void EnviarEmailCadastro(Context context, String remetente, String subject, String mensagem) {
        JavaMailAPI javaMailAPI = new JavaMailAPI(context, remetente, subject, mensagem);

        javaMailAPI.execute();
    }
}
