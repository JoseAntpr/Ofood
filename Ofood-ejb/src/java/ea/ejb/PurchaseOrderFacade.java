/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Item;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.entity.User;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author UNI
 */
@Stateless
public class PurchaseOrderFacade extends AbstractFacade<PurchaseOrder> {
    @EJB
    private ItemFacade itemFacade;
    @EJB
    private RestaurantFacade restaurantFacade;
    @EJB
    private UserFacade userFacade;
    
    
    @PersistenceContext(unitName = "Ofood-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseOrderFacade() {
        super(PurchaseOrder.class);
    }
    
//    public void addItem(Integer iId, PurchaseOrder p, User u, Restaurant r){
//        
//        Item i = itemFacade.find(iId);
//        p.getItemCollection().add(i);
//        edit(p);
//        
//        if(!u.getPurchaseOrderCollection().contains(p)){
//        u.getPurchaseOrderCollection().add(p);
//        }
//        userFacade.edit(u);
//        
//        r.getPurchaseOrderCollection().add(p);
//        restaurantFacade.edit(r);
//    }
    
    public void createPurchaseOrder(PurchaseOrder po, User u, Restaurant r){
        create(po);
        userFacade.edit(u);
        restaurantFacade.edit(r);
    }
}
