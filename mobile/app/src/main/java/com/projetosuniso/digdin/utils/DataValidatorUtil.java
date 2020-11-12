package com.projetosuniso.digdin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataValidatorUtil {

        public static boolean isData(String data) {
            try {
                //SimpleDateFormat é usada para trabalhar com formatação de datas
                //neste caso meu formatador irá trabalhar com o formato "dd/MM/yyyy"
                //dd = dia, MM = mes, yyyy = ano
                //o "M" dessa String é maiusculo porque "m" minusculo se n me engano é minutos
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                //a mágica desse método acontece aqui, pois o setLenient() é usado para setar
                //sua escolha sobre datas estranhas, quando eu seto para "false" estou dizendo
                //que não aceito datas falsas como 31/02/2016
                sdf.setLenient(false);
                //aqui eu tento converter a String em um objeto do tipo date, se funcionar
                //sua data é valida
                sdf.parse(data);
                //se nada deu errado retorna true (verdadeiro)
                return true;
            } catch (ParseException ex) {
                //se algum passo dentro do "try" der errado quer dizer que sua data é falsa, então,
                //retorna falso
                return false;
            }
        }
}
