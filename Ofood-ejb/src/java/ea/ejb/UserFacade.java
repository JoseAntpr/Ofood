/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author UNI
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "Ofood-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User login(String email, String password){        
        User user = null;

        //Creamos una consulta que busque usuarios por email y contraseña.
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email AND u.password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            user = (User) query.getSingleResult();
        } catch (Exception ex) {
        }

        return user;
    }
    
    public User nuevoUser(String name, String address, String phone, String email, String password) {

        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(false);
        
        create(user);
        return user;

    }
    
    public User buscarEmail(String email) {

        Query query = em.createNamedQuery("User.findByEmail");
        query.setParameter("email", email);
        User user = null;

        try {
            user = (User) query.getSingleResult();
        } catch (Exception ex) {
        }

        return user;
    }
}
