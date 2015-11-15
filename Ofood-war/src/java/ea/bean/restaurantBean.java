/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author UNI
 */
@ManagedBean
@RequestScoped
public class restaurantBean {
    

    /**
     * Creates a new instance of restaurantBean
     */
    public restaurantBean() {
    }
    
    @PostConstruct
    public void init(){
        
    }
    
}
