/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.ejb.ItemCategoryFacade;
import ea.ejb.ItemFacade;
import ea.ejb.PurchaseOrderFacade;
import ea.ejb.RestaurantFacade;
import ea.ejb.UserFacade;
import ea.entity.Item;
import ea.entity.ItemCategory;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.entity.Review;
import ea.entity.User;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jesús
 */
@ManagedBean (name = "restaurantBean")
@RequestScoped
public class RestaurantBean {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    @EJB
    private RestaurantFacade restaurantFacade;
    @EJB

    private float mark;

    /**
     * Creates a new instance of restaurantBean
     */
    public RestaurantBean() {
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
    
    @PostConstruct
    public void init(){
        mark = restaurantFacade.getRestaurantMark(loginBean.getRestaurant());
    }
    
    public String cargarRestaurante(Restaurant r){
        loginBean.setRestaurant(r);
        
        mark = restaurantFacade.getRestaurantMark(r);
        purchaseOrder = loginBean.getPurchaseOrder();
        return "restaurant";
    }
    @PreDestroy
    public String addItem(Integer itemId){
        if(itemId != null){
            PurchaseOrder po = loginBean.getPurchaseOrder();
            po.getItemCollection().add(item);
            po.setBill(po.getBill()+item.getPrice());
        }
    }
    
    public void deleteItem(Item item){
        if(item != null){
            PurchaseOrder po = loginBean.getPurchaseOrder();
            po.getItemCollection().remove(item);
            po.setBill(po.getBill()-item.getPrice());
        }
    }
    
}
