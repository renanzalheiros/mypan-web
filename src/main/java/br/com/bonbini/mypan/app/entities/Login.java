package br.com.bonbini.mypan.app.entities;

import br.com.bonbini.mypan.app.util.CryptoUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by andrepereira on 15/08/17.
 */
@Document(collection = "Login")
public class Login {

    @Id
    private String id;
    private String email;
    private String token;

    public Login() {
    }

    public Login(String email) {
        this.email = email;
        if(email != null){
            token = CryptoUtil.criptoStringMD5(String.valueOf(Math.random()*1000000.0).concat(email));
        }
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return this.token;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
