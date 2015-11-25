/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Item;
import ea.entity.ItemOrder;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author UNI
 */
@Stateless
public class PurchaseOrderFacade extends AbstractFacade<PurchaseOrder> {
    @EJB
    private ItemOrderFacade itemOrderFacade;
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
        po.setState("Pedido listo");
        po.getItemOrderCollection().stream().forEach((io) -> {
            itemOrderFacade.create(io);
        });
        create(po);
        userFacade.edit(u);
        restaurantFacade.edit(r);
    }
    
    public PurchaseOrder findById(Integer id) {
        PurchaseOrder po = (PurchaseOrder) em.createNamedQuery("findById").setParameter("id",id).getSingleResult();
        return po;
    }
    
    public List<Object[]> findOrderByDayInMonth(int mes){
        Query query;
        query = em.createNativeQuery("SELECT EXTRACT(DAY from date), COUNT(date) "
        + "FROM purchase_order "
        + "WHERE (EXTRACT(MONTH FROM date)= ?) AND (EXTRACT(YEAR FROM date)=YEAR(CURDATE())) "
        + "GROUP BY EXTRACT(DAY FROM date)").setParameter(1,mes);
        
        List<Object[]> lista= query.getResultList();
        
        return lista;
    }
    
    public List<Object[]> findUserInRestaurant(){
        Query query = em.createQuery("SELECT p.restaurantId.name, COUNT(DISTINCT p.userId) FROM PurchaseOrder p GROUP BY p.restaurantId");
        List<Object[]> lista= query.getResultList();
        
        return lista;
    }
    public Integer findTotalPurchaseOrderByZipCode(Integer zipcode){
        Integer totalPurchase = Integer.parseInt( em.createNativeQuery("select count(p.id) from purchase_order p INNER JOIN  restaurant r ON p.restaurant_id=r.id where r.zipcode= ?").setParameter(1, zipcode).getSingleResult().toString());
        return totalPurchase;
    }
}
