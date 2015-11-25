/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ws;

import ea.ejb.ItemOrderFacade;
import ea.ejb.PurchaseOrderFacade;
import ea.ejb.RestaurantFacade;
import ea.entity.ItemOrder;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Jesus
 */
@WebService(serviceName = "OfoodWebService")
@Stateless()
public class OfoodWebService {
    @EJB
    private RestaurantFacade restaurantFacade;
    
    @EJB
    private PurchaseOrderFacade purchaseOrderFacade;
    
    @EJB
    private ItemOrderFacade itemOrderFacade;

    @WebMethod(operationName = "topFiveFood")
    public List<Object[]> topFiveFood() {
        return itemOrderFacade.topFiveFood();
    }
    
    
    @WebMethod(operationName = "getRestaurantPuntuationOrdered")
    public List<Object[]> getRestaurantPuntuationOrdered() {
        return restaurantFacade.getRestaurantPuntuationOrdered();
    }
    
    @WebMethod(operationName = "findOrderByDayInMonth")
    public List<Object[]> findOrderByDayInMonth(@WebParam(name="mes") int mes) {
        return purchaseOrderFacade.findOrderByDayInMonth(mes);
    }
    
    @WebMethod(operationName = "findUserInRestaurant")
    public List<Object[]> findUserInRestaurant() {
        return purchaseOrderFacade.findUserInRestaurant();
    }
    
    @WebMethod(operationName = "findTotalPurchaseOrderByZipCode")
    public Integer findTotalPurchaseOrderByZipCode(@WebParam(name="zipCode") Integer zipCode) {
        Integer res = purchaseOrderFacade.findTotalPurchaseOrderByZipCode(zipCode);
        System.out.println(res);
        return res;
    }

}
