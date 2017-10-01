package br.com.bonbini.mypan.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Document(collection = "Product")
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private String file;
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String description, String file, Double price) {
        this.name = name;
        this.description = description;
        this.file = file;
        this.price = BigDecimal.valueOf(price);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nomeProduto) {
        this.name = nomeProduto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
