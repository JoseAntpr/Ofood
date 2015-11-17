/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author UNI
 */
@Entity
@Table(name = "purchase_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrder.findAll", query = "SELECT p FROM PurchaseOrder p"),
    @NamedQuery(name = "PurchaseOrder.findById", query = "SELECT p FROM PurchaseOrder p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseOrder.findByDate", query = "SELECT p FROM PurchaseOrder p WHERE p.date = :date"),
    @NamedQuery(name = "PurchaseOrder.findByState", query = "SELECT p FROM PurchaseOrder p WHERE p.state = :state"),
    @NamedQuery(name = "PurchaseOrder.findByBill", query = "SELECT p FROM PurchaseOrder p WHERE p.bill = :bill"),
    @NamedQuery(name = "PurchaseOrder.findByAddress", query = "SELECT p FROM PurchaseOrder p WHERE p.address = :address")})
public class PurchaseOrder implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrderId")
    private Collection<ItemOrder> itemOrderCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bill")
    private float bill;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "address")
    private String address;
    @ManyToMany(mappedBy = "purchaseOrderCollection")
    private Collection<Item> itemCollection;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Restaurant restaurantId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id) {
        this.id = id;
    }

    public PurchaseOrder(Integer id, Date date, String state, float bill, String address) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.bill = bill;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getBill() {
        return bill;
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ea.entity.PurchaseOrder[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ItemOrder> getItemOrderCollection() {
        return itemOrderCollection;
    }

    public void setItemOrderCollection(Collection<ItemOrder> itemOrderCollection) {
        this.itemOrderCollection = itemOrderCollection;
    }
    
}
