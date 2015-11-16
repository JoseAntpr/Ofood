/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.ejb.RestaurantFacade;
import ea.entity.Item;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jesús
 */
@ManagedBean (name = "restaurantBean")
@RequestScoped
public class RestaurantBean {
    @EJB
    private RestaurantFacade restaurantFacade;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
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
    
  
    
    public String cargarRestaurante(Restaurant r){
        loginBean.setRestaurant(r);
        mark = restaurantFacade.getRestaurantMark(r);
        return "restaurant";
    }
   
    
    public void addItem(Item item){
        if(item != null){
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
    
    public float totalAmount(){
        return loginBean.getPurchaseOrder().getBill()+1;
    }
    
}
