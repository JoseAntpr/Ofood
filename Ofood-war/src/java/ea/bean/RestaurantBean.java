/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.ejb.RestaurantFacade;
import ea.entity.Item;
import ea.entity.ItemOrder;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
    
    public String cargarRestaurante(Restaurant r){
        loginBean.setRestaurant(r);
        return "restaurant";
    }
   
    
    public void addItem(Item item){
        if(item != null){
            PurchaseOrder po = loginBean.getPurchaseOrder();
            List<ItemOrder> ioL = (LinkedList<ItemOrder>) po.getItemOrderCollection(); // Set de ItemOrder
            boolean finded = false;
            for(int i=0; i<ioL.size() && finded == false; i++){
                ItemOrder io = ioL.get(i);
                if(io.getItemId().equals(item)){
                    io.setCount(io.getCount()+1);
                    finded = true;
                }
            }
            if(finded == false){
                ItemOrder io = new ItemOrder();
                io.setItemId(item);
                io.setPurchaseOrderId(po);
                io.setCount(1);
                ioL.add(io);
            }
            
//            po.getItemCollection().add(item);
            po.setBill(po.getBill()+item.getPrice());
        }
    }
    
    public void deleteItem(ItemOrder itemOrder){
        if(itemOrder != null){
            PurchaseOrder po = loginBean.getPurchaseOrder();
            List<ItemOrder> ioL = (LinkedList<ItemOrder>) po.getItemOrderCollection(); // Set de ItemOrder
            boolean finded = false;
            for(int i=0; i<ioL.size() && finded == false; i++){
                ItemOrder io = ioL.get(i);
                if(io.getItemId().equals(itemOrder.getItemId())){
                    io.setCount(io.getCount()-1);
                    finded = true;
                    if(io.getCount() == 0){
                        ioL.remove(i);
                    }
                }
                
            }
            po.setBill(po.getBill()-itemOrder.getItemId().getPrice());
        }
    }
    
    public float mark(){
        return restaurantFacade.getRestaurantMark(loginBean.getRestaurant());
    }
    
    public String priceItem(Item item){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(item.getPrice());
    }
    
    public String priceItemOrder(ItemOrder io){
        DecimalFormat df = new DecimalFormat("0.00");
        float number = io.getItemId().getPrice() * io.getCount();
        return df.format(number);
    }
    
    public String bill(){
        DecimalFormat df = new DecimalFormat("0.00");
        float number = loginBean.getPurchaseOrder().getBill();
        return df.format(number);
    }
    
    public String totalAmount(){
        DecimalFormat df = new DecimalFormat("0.00");
        float number = loginBean.getPurchaseOrder().getBill()+1;
        return df.format(number);
    }
    
    public String doPay(){
        return "pay";
    }
    
}
