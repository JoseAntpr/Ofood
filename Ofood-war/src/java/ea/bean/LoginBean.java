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
import java.util.LinkedList;
import java.util.List;
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
    private String name;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String passwordRepeat;
    private User user=null;
    String error;
    
    
    private PurchaseOrder purchaseOrder;
    private Restaurant restaurant;
    private Item item;
    private List<Restaurant> listRestaurant;
    
    private boolean fromOrder;
    private boolean pagado;
    
    
    /**
     * Creates a new instance of sessionBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init(){
      
        infoSession = "Iniciar sesión...";
        purchaseOrder = new PurchaseOrder();
//        purchaseOrder.setItemCollection(new LinkedList());
        purchaseOrder.setBill(0.0f);
        purchaseOrder.setItemOrderCollection(new LinkedList());
        
        pagado = false;
    } 
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Restaurant> getListRestaurant() {
        return listRestaurant;
    }

    public boolean getPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isFromOrder() {
        return fromOrder;
    }

    public void setFromOrder(boolean fromOrder) {
        this.fromOrder = fromOrder;
    }

    public void setListRestaurant(List<Restaurant> listRestaurant) {
        this.listRestaurant = listRestaurant;
    }
    

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
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
    
     public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    
    
    
    public String checkUser(boolean order){
        String ruta = "index";
        user = userFacade.login(email, password);
        if(user == null){
            error = "El usuario o contraseña son erroneos";
            if(order == true){
                ruta = "iniciarSesion";
            }
        }else{
            infoSession = "Hola " + user.getName();
            if(order == true){
                setPagado(false);
                ruta = "pay";
            }
        }
        
        return ruta;
    }
    
    public String registro(boolean order){
        fromOrder = order;
        return "registro";
    }
    
    public String registrarUsuario() {
        String ruta;
        User usr = userFacade.buscarEmail(email);

        if (usr == null) {
            if(password.equals(passwordRepeat)){
                error="";
                usr = userFacade.nuevoUser(this.name, this.address, this.phone, this.email, this.password);
                this.user = usr;
                infoSession = "Hola " + user.getName();
                    if(fromOrder == true){
                        setPagado(false);
                        ruta = "pay";
                    }else{
                        ruta = "index";
                    }
                    fromOrder = false;
                
            }else{
                error = "Las contraseñas no coinciden";
                ruta = "registro";
            }
        } else {
            error = "El email ya esta registrado en nuestra red social.";
            ruta = "registro";
        }
        return ruta;

    }
    
    public String inicio(){
        if(pagado){
            pagado=false;
        }
        return "index";
    }
    

    public String salir(){
        setInfoSession("Iniciar sesión...");
        setName("");
        setEmail("");
        setAddress("");
        setPhone("");
        setPassword("");
        setPasswordRepeat("");
        setUser(null);
        setError("");
    
        setPurchaseOrder(null);
        setRestaurant(null);
        setItem(null);
        
        setFromOrder(false);
        setPagado(false);
        return "index";
        
    }
    
    
}
