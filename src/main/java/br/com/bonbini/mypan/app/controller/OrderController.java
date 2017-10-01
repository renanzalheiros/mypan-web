package br.com.bonbini.mypan.app.controller;

import br.com.bonbini.mypan.app.dao.BuyOrderDao;
import br.com.bonbini.mypan.app.dao.LoginDao;
import br.com.bonbini.mypan.app.entities.BuyOrder;
import br.com.bonbini.mypan.app.entities.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/rest/order")
public class OrderController {

    @Autowired
    LoginDao loginDao;

    @Autowired
    BuyOrderDao buyOrderDao;

    @RequestMapping(value = "/neworder", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BuyOrder newOrder(@RequestHeader("user-auth") String auth, @RequestBody BuyOrder buyOrder){
        Login user = loginDao.findByToken(auth);
        if(user != null) {
            return buyOrderDao.save(buyOrder);
        }

        return null;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BuyOrder> getOrders(){
        return buyOrderDao.findAll();
    }

    @RequestMapping(value = "/myorders", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BuyOrder> getUserOrders(@RequestHeader("user-auth") String auth){
        Login user = loginDao.findByToken(auth);
        if(user != null) {
            return buyOrderDao.findByUser(user);
        }

        return null;
    }
}
