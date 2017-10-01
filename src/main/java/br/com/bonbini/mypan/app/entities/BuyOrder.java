package br.com.bonbini.mypan.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document(collection = "BuyOrder")
public class BuyOrder {

    @Id
    private String id;
    private List<OrderSku> orderSkuList;
    private String userEmail;
    private Date date;
    private BigDecimal buyOrderPrice;
    private String orderStatus;

    public BuyOrder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderSkuList(List<OrderSku> orderSkuList) {
        this.orderSkuList = orderSkuList;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<OrderSku> getOrderSkuList(){
        return this.orderSkuList;
    }


    public String getUserEmail(){
        return this.userEmail;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date orderDate) {
        this.date = orderDate;
    }

    public BigDecimal getBuyOrderPrice() {
        return buyOrderPrice;
    }

    public void setBuyOrderPrice(BigDecimal buyOrderPrice) {
        this.buyOrderPrice = buyOrderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
