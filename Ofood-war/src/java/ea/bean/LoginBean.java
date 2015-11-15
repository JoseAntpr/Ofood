/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.ejb.UserFacade;
import ea.entity.Item;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.entity.User;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author UNI
 */
@ManagedBean (name = "loginBean")
@SessionScoped
public class LoginBean {
    @EJB
    private UserFacade userFacade;

    private String infoSession;
    private String email;
    private String password;
    private User user;
    String error;
    
    private PurchaseOrder purchaseOrder;
    private Restaurant restaurant;
    private Item item;
    
    
    /**
     * Creates a new instance of sessionBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init(){
    
        // Temporalmente forzamos el user 1
        //user = userFacade.find(1);
        infoSession = "Iniciar sesión...";
        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfoSession() {
        return infoSession;
    }

    public void setInfoSession(String infoSession) {
        this.infoSession = infoSession;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
    public String checkUser(){
        String ruta = "index";
        user = userFacade.login(email, password);
        if(user == null){
            error = "El usuario o contraseña son erroneos";
        }else{
            infoSession = "Hola " + user.getName();
            //pruebas
//            ruta = "restaurant";
        }
        return ruta;
    }
    
    @PreDestroy
    public void mecagoento(){
        String s = "s";
        System.out.println("noooooooo");
    }
    
}
