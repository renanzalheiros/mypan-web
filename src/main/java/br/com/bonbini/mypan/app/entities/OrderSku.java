package br.com.bonbini.mypan.app.entities;

import java.math.BigDecimal;

public class OrderSku {

    private Product product;
    private int quantity;
    private BigDecimal price;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
