package br.com.bonbini.mypan.app.dao;

import br.com.bonbini.mypan.app.entities.BuyOrder;
import br.com.bonbini.mypan.app.entities.Login;
import br.com.bonbini.mypan.app.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BuyOrderDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public BuyOrder save(BuyOrder buyOrder) {
        mongoTemplate.insert(buyOrder, "BuyOrder");
        return buyOrder;

    }

    public List<BuyOrder> findByUser(Login user) {
        return mongoTemplate.find(new Query(Criteria.where("userEmail").is(user.getEmail())), BuyOrder.class, "BuyOrder");
    }

    public List<BuyOrder> findAll() {
        return mongoTemplate.findAll(BuyOrder.class, "BuyOrder");
    }

    public BuyOrder updateStatus(BuyOrder buyOrder, String status) {
        Update update = new Update();
        update.set("orderStatus", status);
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(buyOrder.getId())), update, BuyOrder.class);
        return buyOrder;
    }

    public BuyOrder findOrderById(String buyOrderId) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(buyOrderId)), BuyOrder.class);
    }

    public BuyOrder delete(BuyOrder orderById) {
        Update update = new Update();
        update.set("orderStatus", Constantes.REJEITADO);
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(orderById.getId())), update, BuyOrder.class);
        return orderById;
    }
}
