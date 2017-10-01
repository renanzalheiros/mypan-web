package br.com.bonbini.mypan.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by andrepereira on 13/08/17.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "zalho";
    }
}
