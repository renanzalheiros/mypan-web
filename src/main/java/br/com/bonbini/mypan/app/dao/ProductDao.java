package br.com.bonbini.mypan.app.dao;

import br.com.bonbini.mypan.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Product> findAll() {
        return mongoTemplate.findAll(Product.class, "Product");
    }

    public Product saveNewProduct(Product product) {
        mongoTemplate.save(product, "Product");
        return product;
    }
}
