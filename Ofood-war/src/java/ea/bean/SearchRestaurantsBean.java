/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.ejb.RestaurantFacade;
import ea.entity.Restaurant;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jesus
 */
@ManagedBean
@RequestScoped
public class SearchRestaurantsBean {
    

    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    @EJB
    private RestaurantFacade restaurantFacade;
    
    private List<Restaurant> restaurantList;
    private String entrega;
    private int totalRestaurantes;
    
    /**
     * Creates a new instance of SearchRestaurantsBean
     */
    public SearchRestaurantsBean() {
    }
    
    @PostConstruct
    public void init(){
        restaurantList=restaurantFacade.getRestaurantList("29005");
        if(restaurantList!=null){
            totalRestaurantes=restaurantList.size();
            if(totalRestaurantes==1){
                entrega=" restaurante que entrega en 29005, ";
            }else{
                entrega=" restaurantes que entregan en 29005, ";
            }
            entrega= entrega + restaurantList.get(0).getCity();
        }else{
            totalRestaurantes=0;
            entrega=" restaurantes que entregan en 29005";
        }
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public int getTotalRestaurantes() {
        return totalRestaurantes;
    }

    public void setTotalRestaurantes(int totalRestaurantes) {
        this.totalRestaurantes = totalRestaurantes;
    }
    
    
    
}
