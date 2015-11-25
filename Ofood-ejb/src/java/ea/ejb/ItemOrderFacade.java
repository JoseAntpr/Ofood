/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.ItemOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author UNI
 */
@Stateless
public class ItemOrderFacade extends AbstractFacade<ItemOrder> {
    @PersistenceContext(unitName = "Ofood-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemOrderFacade() {
        super(ItemOrder.class);
    }
     
    public List<Object[]> TopFiveFood(){
       /* Query query = em.createQuery("select  from item_order,item "
                + "where item_id<6 AND item_id=item.id group by item_id order by SUM(count) DESC");
        List<String> topFive =null;
        
        topFive =( List<String>) query.getResultList();
        return topFive;
        */
        
        Query query = em.createQuery("select i,SUM(io.count) AS cantidad from Item i,ItemOrder io where i.id <6 AND i.id=io.id group by i.id ORDER BY cantidad");
        List<Object[]> topfive=query.getResultList();
        return topfive ;
    }
    
}
