/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;


import ea.ws.OfoodWebService_Service;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.util.List;
import net.java.dev.jaxb.array.AnyTypeArray;

/**
 *
 * @author Joseantpr
 */
@ManagedBean
@SessionScoped
public class RestauranteClient {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/OfoodWebService/OfoodWebService.wsdl")
    private ea.ws.OfoodWebService_Service service_1;

    private List<net.java.dev.jaxb.array.AnyTypeArray> topfood;
    private String zipCode;
    private Integer totalPedidos;
    private List<net.java.dev.jaxb.array.AnyTypeArray> puntuationRestaurant;
    private List<net.java.dev.jaxb.array.AnyTypeArray> orderByDay;
    private List<net.java.dev.jaxb.array.AnyTypeArray> userInRestaurant;
    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
    

    public List<AnyTypeArray> getPuntuationRestaurant() {
        return puntuationRestaurant;
    }

    public void setPuntuationRestaurant(List<AnyTypeArray> puntuationRestaurant) {
        this.puntuationRestaurant = puntuationRestaurant;
    }

    public List<AnyTypeArray> getOrderByDay() {
        return orderByDay;
    }

    public void setOrderByDay(List<AnyTypeArray> orderByDay) {
        this.orderByDay = orderByDay;
    }

    public List<AnyTypeArray> getUserInRestaurant() {
        return userInRestaurant;
    }

    public void setUserInRestaurant(List<AnyTypeArray> userInRestaurant) {
        this.userInRestaurant = userInRestaurant;
    }

    
    public Integer getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Integer totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    
    
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    
    
    public OfoodWebService_Service getService_1() {
        return service_1;
    }

    public void setService_1(OfoodWebService_Service service_1) {
        this.service_1 = service_1;
    }

    public List<AnyTypeArray> getTopfood() {
        return topfood;
    }

    public void setTopfood(List<AnyTypeArray> topfood) {
        this.topfood = topfood;
    }
    

    private java.util.List<net.java.dev.jaxb.array.AnyTypeArray> topFiveFood() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ea.ws.OfoodWebService port = service_1.getOfoodWebServicePort();
        return port.topFiveFood();
    }

    private Integer findTotalPurchaseOrderByZipCode(java.lang.Integer zipCode) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ea.ws.OfoodWebService port = service_1.getOfoodWebServicePort();
        return port.findTotalPurchaseOrderByZipCode(zipCode);
    }
    
    

    private java.util.List<net.java.dev.jaxb.array.AnyTypeArray> getRestaurantPuntuationOrdered() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ea.ws.OfoodWebService port = service_1.getOfoodWebServicePort();
        return port.getRestaurantPuntuationOrdered();
    }

    private java.util.List<net.java.dev.jaxb.array.AnyTypeArray> findOrderByDayInMonth(int mes) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ea.ws.OfoodWebService port = service_1.getOfoodWebServicePort();
        return port.findOrderByDayInMonth(mes);
    }

    private java.util.List<net.java.dev.jaxb.array.AnyTypeArray> findUserInRestaurant() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ea.ws.OfoodWebService port = service_1.getOfoodWebServicePort();
        return port.findUserInRestaurant();
    }
    public String topFivePage(){
       topfood= topFiveFood();
        return "topFiveFoodPage";
    }
    public String inicio(){
        totalPedidos=null;
        return"index";
    }
    public String totalPurchaseOrder(){
        totalPedidos=null;
        totalPedidos = findTotalPurchaseOrderByZipCode(Integer.parseInt(zipCode));
        return "index";
    }
    public String puntuationOrdered (){
        puntuationRestaurant = getRestaurantPuntuationOrdered();
        return "puntuationOrderedRestaurant";
    }
    public String orderByDay(){
        orderByDay = findOrderByDayInMonth(Integer.parseInt(mes));
        return"orderByMonthPage";
    }
    public String userInRestaurant(){

        userInRestaurant =  findUserInRestaurant();
        return "userInRestaurantPage";
    }
    public String indextoOrderByDay(){
        orderByDay = null;
        return "orderByMonthPage";
    }
    

    

    /**
     * Creates a new instance of RestauranteClient
     */

    
}
