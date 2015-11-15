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
 * @author UNI
 */
@ManagedBean (name = "restaurantBean")
@RequestScoped
public class RestaurantBean {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private RestaurantFacade restaurantFacade;
    @EJB
    private ItemCategoryFacade itemCategoryFacade;
    @EJB
    private ItemFacade itemFacade;
    @EJB
    private PurchaseOrderFacade purchaseOrderFacade;
    
    private User user;
    
    private Collection<ItemCategory> itemCategoryList;
    private PurchaseOrder purchaseOrder;
    private Restaurant restaurant;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<ItemCategory> getItemCategoryList() {
        return itemCategoryList;
    }

    public void setItemCategoryList(Collection<ItemCategory> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    
    
    
    @PostConstruct
    public void init(){
            // Recuperar el usuario de la sesion
            // Temporalmente
            user = loginBean.getUser();
//            user = userFacade.find(1);

            // Ya tenemos el id del restaurante
            Integer restaurantId = 2;
            restaurant = restaurantFacade.find(restaurantId);

            // Calculamos la media de la puntuación
            mark = restaurantFacade.getRestaurantMark(restaurant);

            if (restaurant != null){
                itemCategoryList = itemCategoryFacade.findItemCategoryFromRestaurant(restaurantId);
            }else{
                System.out.println("Restaurante Nulo");
            }
            
            purchaseOrder = loginBean.getPurchaseOrder();
            
        
    }
    
    @PreDestroy
    public String addItem(Integer itemId){
        if(itemId != null){
            PurchaseOrder po = loginBean.getPurchaseOrder();
            if (po == null){
                po = new PurchaseOrder(new Date(), "En proceso", 0.0f, loginBean.getUser().getAddress());
            }
            purchaseOrderFacade.addItem(itemId, po, user, restaurant);
            loginBean.setPurchaseOrder(po);
            loginBean.setItem(null);
        }
        return "restaurant";
    }
    
}
