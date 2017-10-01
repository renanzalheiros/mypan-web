package br.com.bonbini.mypan.app.dao;

import br.com.bonbini.mypan.app.entities.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by andrepereira on 15/08/17.
 */
@Repository
public class LoginDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public Login findOne(String userId) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(userId)), Login.class, "Login");
    }

    public Login save(Login login) {
        mongoTemplate.save(login, "Login");
        return login;
    }

    public Login findByToken(String token) {
        return mongoTemplate.findOne(new Query(Criteria.where("token").is(token)), Login.class, "Login");
    }

    public Login findByEmail(String email) {
        try{
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), Login.class, "Login");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
