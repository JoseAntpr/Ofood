/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Restaurant;
import ea.entity.Review;
import java.util.Collection;
import java.util.LinkedList;
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
public class RestaurantFacade extends AbstractFacade<Restaurant> {
    @PersistenceContext(unitName = "Ofood-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestaurantFacade() {
        super(Restaurant.class);
    }
    
    public float getRestaurantMark(Restaurant r){
        float mark = (float) 0.0;
        Collection<Review> l = r.getReviewCollection();
        for(Review re : l){
            mark += re.getMark();
        }
        if(!l.isEmpty()){
            mark=mark / l.size();
        }
        return mark;
    }
    
   
    public List<Restaurant> getRestaurantList(String zipcode){
        Query query=em.createNamedQuery("Restaurant.findByZipcode");
        query.setParameter("zipcode", zipcode);
        List<Restaurant> list=null;
        
        try {
            list = (List<Restaurant>) query.getResultList();
        } catch (Exception ex) {
        }
        if(list.size()==0){
            list=null;
        }
        
        return list;
    }
    
    public List<Object[]> getRestaurantPuntuationOrdered(){
    // "select name from restaurant, review where restaurant_id = restaurant.id group by restaurant_id order by SUM(mark) DESC        
        Query q = em.createQuery( "SELECT r.name, (SUM(re.mark)*1.0)/COUNT(re) as mark FROM Restaurant r, Review re "
                                + "WHERE r.id = re.restaurantId.id "
                                + "GROUP BY r.id ORDER BY mark DESC");
        List<Object[]> list = new LinkedList();
        try {
            list = new LinkedList(q.getResultList());
        } catch (Exception ex) {
            
        }
        return list;
    }
    
}
