/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.bean;

import com.google.gson.Gson;
import ea.ejb.RestaurantFacade;
import ea.entity.Item;
import ea.entity.ItemOrder;
import ea.entity.PurchaseOrder;
import ea.entity.Restaurant;
import ea.flickrws.JerseyFlickr;
import ea.flickrws.Photo;
import ea.flickrws.Photo_;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jesús
 */
@ManagedBean(name = "restaurantBean")
@RequestScoped
public class RestaurantBean {

    @EJB
    private RestaurantFacade restaurantFacade;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    JerseyFlickr clientFlickr = new JerseyFlickr();

    /**
     * Creates a new instance of restaurantBean
     */
    public RestaurantBean() {
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String cargarRestaurante(Restaurant r) {
        loginBean.setRestaurant(r);
        PurchaseOrder po = new PurchaseOrder();
        po.setBill(0.0f);
        po.setItemOrderCollection(new LinkedList());
        loginBean.setPurchaseOrder(po);

        return "restaurant";
    }

    public void addItem(Item item) {
        if (item != null) {
            PurchaseOrder po = loginBean.getPurchaseOrder();
            List<ItemOrder> ioL = (LinkedList<ItemOrder>) po.getItemOrderCollection(); // Set de ItemOrder
            boolean finded = false;
            for (int i = 0; i < ioL.size() && finded == false; i++) {
                ItemOrder io = ioL.get(i);
                if (io.getItemId().equals(item)) {
                    io.setCount(io.getCount() + 1);
                    finded = true;
                }
            }
            if (finded == false) {
                ItemOrder io = new ItemOrder();
                io.setItemId(item);
                io.setPurchaseOrderId(po);
                io.setCount(1);
                ioL.add(io);
            }

//            po.getItemCollection().add(item);
            po.setBill(po.getBill() + item.getPrice());
        }
    }

    public void deleteItem(ItemOrder itemOrder) {
        if (itemOrder != null) {
            PurchaseOrder po = loginBean.getPurchaseOrder();
            List<ItemOrder> ioL = (LinkedList<ItemOrder>) po.getItemOrderCollection(); // Set de ItemOrder
            boolean finded = false;
            for (int i = 0; i < ioL.size() && finded == false; i++) {
                ItemOrder io = ioL.get(i);
                if (io.getItemId().equals(itemOrder.getItemId())) {
                    io.setCount(io.getCount() - 1);
                    finded = true;
                    if (io.getCount() == 0) {
                        ioL.remove(i);
                    }
                }

            }
            po.setBill(po.getBill() - itemOrder.getItemId().getPrice());
        }
    }

    public float mark() {
        float mark = restaurantFacade.getRestaurantMark(loginBean.getRestaurant());
        if (mark == 0.0) {
            mark = 0;
        }
        return mark;
    }

    public String priceItem(Item item) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(item.getPrice());
    }

    public String priceItemOrder(ItemOrder io) {
        DecimalFormat df = new DecimalFormat("0.00");
        float number = io.getItemId().getPrice() * io.getCount();
        return df.format(number);
    }

    public String bill() {
        DecimalFormat df = new DecimalFormat("0.00");
        float number = loginBean.getPurchaseOrder().getBill();
        return df.format(number);
    }

    public String totalAmount() {
        DecimalFormat df = new DecimalFormat("0.00");
        float number = loginBean.getPurchaseOrder().getBill() + 1;
        return df.format(number);
    }

    public String doPay() {
        String ruta = "iniciarSesion";

        if (loginBean.getUser() != null) { //Si hay usuario logeado
            ruta = "pay";
        }

        if (loginBean.getPurchaseOrder().getItemOrderCollection().isEmpty()) { //Si no hay item en el order
            ruta = "restaurant";
        }

        return ruta;
    }

    public String findPhoto(Item i) throws IOException {
//        HttpServletRequest request = (HttpServletRequest) FacesContext
//                .getCurrentInstance()
//                .getExternalContext()
//                .getRequest();
//        HttpServletResponse response = (HttpServletResponse) FacesContext
//                .getCurrentInstance()
//                .getExternalContext()
//                .getResponse();
//        
//        clientFlickr.login(request, response, "43ff752bd5cdb2d64fbfa75d6b4c29f6", "fa2360787486d080");
//        String r = clientFlickr.photos_search(String.class, s);
//        r = r.replace("jsonFlickrApi(", "");
//        r = r.substring(0, r.length() - 1);
        String idFlickr=i.getFlickrId();
        String img="";
        if(idFlickr!=null){
            //"https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&api_key=43ff752bd5cdb2d64fbfa75d6b4c29f6&tags='"+s+"'"
            URL url=new URL("https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=43ff752bd5cdb2d64fbfa75d6b4c29f6&photo_id="+idFlickr+"&format=json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            String reply="";
            while ((line = reader.readLine()) != null) { 
                reply += line; 
            }
            reader.close();

            reply = reply.replace("jsonFlickrApi(", "");
            reply= reply.substring(0, reply.length() - 1);

            Gson gson = new Gson();
            Photo pjson=gson.fromJson(reply, Photo.class);
    //        PhotosJSON pjson = gson.fromJson(reply, PhotosJSON.class);
    //        Photos photos = pjson.getPhotos();
    //        List<Photo> listaPhotos = photos.getPhoto();

            //http://farmX.staticflickr.com/SERVER/ID_SECRET_size.jpg
            //http://farm8.staticflickr.com/7579/15672113757_958d8bc3a7_z.jpg
            Photo_ p = pjson.getPhoto();
            int farm = p.getFarm();
            String server = p.getServer();
            String id = p.getId();
            String secret = p.getSecret();

            img = "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_t.jpg";
        }
        return img;
    }

}
