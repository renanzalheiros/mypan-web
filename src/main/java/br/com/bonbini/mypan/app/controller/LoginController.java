package br.com.bonbini.mypan.app.controller;

import br.com.bonbini.mypan.app.dao.LoginDao;
import br.com.bonbini.mypan.app.entities.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by andrepereira on 15/08/17.
 */
@RestController
@RequestMapping("/rest/login")
public class LoginController {

    @Autowired
    private LoginDao dao;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Login saveFacebookUser(@RequestBody Login login){
        Login user;

        try {
            user = dao.findByEmail(login.getEmail());
            if(user == null){
                user = dao.save(new Login(login.getEmail()));
            }

            return user;
        } catch (Exception e){
            user = dao.save(new Login(login.getEmail()));
            return user;
        }
    }
}
