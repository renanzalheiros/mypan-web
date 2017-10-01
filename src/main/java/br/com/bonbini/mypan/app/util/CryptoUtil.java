package br.com.bonbini.mypan.app.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andrepereira on 15/08/17.
 */
public class CryptoUtil {

    public static String criptoStringMD5(String valor) {
        String texto = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.reset();
            md5.update(valor.getBytes());

            byte[] digest = md5.digest();

            BigInteger bigInteger = new BigInteger(1, digest);

            texto = bigInteger.toString();

            while (texto.length() < 32) {
                texto = "0"+texto;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return texto;
    }
}
