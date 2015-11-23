/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import ea.ejb.PurchaseOrderFacade;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.entity.User;
import java.util.Date;
import javax.ejb.EJB;
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
    @EJB
    private PurchaseOrderFacade purchaseOrderFacade;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    public Integer poId;
    
    /**
     * Creates a new instance of payBean
     */
    public PayBean() {
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Integer doPurchaseOrderCreate(){
        /* Falta comprobar si está registrado, 
        si no lo está hay que mandarlo al registro y volver
        si lo está  meter el purchase order de memoria en base de datos
        cargarla de nuevo y coger su id para mostrarlo.
        */
        
        PurchaseOrder po;
         poId = -1;
        if (loginBean.getUser() != null){
            po= loginBean.getPurchaseOrder();
            po.setDate(new Date());
            po.setState("Pedido listo");


            po.setAddress(loginBean.getUser().getAddress());
            po.setRestaurantId(loginBean.getRestaurant());
            po.setUserId(loginBean.getUser());
            loginBean.getRestaurant().getPurchaseOrderCollection().add(po);
            loginBean.getUser().getPurchaseOrderCollection().add(po);
            purchaseOrderFacade.createPurchaseOrder(po, loginBean.getUser(),
                            loginBean.getRestaurant());
            poId = po.getId();
        }
        
        return poId;
    }
    


    public String doPay(){
   //     PurchaseOrder po = purchaseOrderFacade.findById(poId);
      //  po.setState("Pagado");
      
        return "index";
    }
    
}
