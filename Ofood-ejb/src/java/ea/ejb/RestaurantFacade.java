/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Restaurant;
import ea.entity.Review;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return mark / l.size();
    }
}
