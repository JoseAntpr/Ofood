/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.ItemCategory;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author UNI
 */
@Stateless
public class ItemCategoryFacade extends AbstractFacade<ItemCategory> {
    @PersistenceContext(unitName = "Ofood-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemCategoryFacade() {
        super(ItemCategory.class);
    }
    
    public Collection<ItemCategory> findItemCategoryFromRestaurant(Integer id){
        Query q = em.createQuery("SELECT i FROM ItemCategory i WHERE i.restaurantId.id = :ID ORDER BY i.id");
        q.setParameter("ID", id);
        return q.getResultList();
    }
    
}
