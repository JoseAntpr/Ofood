/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author UNI
 */
@ManagedBean (name = "payBean")
@RequestScoped
public class PayBean {
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    PurchaseOrder purchaseOrder;
    Restaurant restaurant;
    User user;
    
    /**
     * Creates a new instance of payBean
     */
    public PayBean() {
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    public String doPay(){
        purchaseOrder = loginBean.getPurchaseOrder();
        restaurant = loginBean.getRestaurant();
        user = loginBean.getUser();
        return "pay";
    }
    
}
