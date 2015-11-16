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
    

    private String entrega;
    private int totalRestaurantes;
    private String zipCode;
    /**
     * Creates a new instance of SearchRestaurantsBean
     */
    public SearchRestaurantsBean() {
    }
    
    
    public String buscar(){
        loginBean.setListRestaurant(restaurantFacade.getRestaurantList(zipCode));
        if(loginBean.getListRestaurant()!=null){
            totalRestaurantes=loginBean.getListRestaurant().size();
            if(totalRestaurantes==1){
                entrega=" restaurante que entrega en "+zipCode+", ";
            }else{
                entrega=" restaurantes que entregan en "+zipCode+", ";
            }
            entrega= entrega + loginBean.getListRestaurant().get(0).getCity();
        }else{
            totalRestaurantes=0;
            entrega=" restaurantes que entregan en "+zipCode;
        }
        return "restaurantList";
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    
    
}
