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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author Jesus
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
    @NamedQuery(name = "Order.findById", query = "SELECT o FROM Order o WHERE o.id = :id"),
    @NamedQuery(name = "Order.findByDate", query = "SELECT o FROM Order o WHERE o.date = :date"),
    @NamedQuery(name = "Order.findByState", query = "SELECT o FROM Order o WHERE o.state = :state"),
    @NamedQuery(name = "Order.findByBill", query = "SELECT o FROM Order o WHERE o.bill = :bill"),
    @NamedQuery(name = "Order.findByAddres", query = "SELECT o FROM Order o WHERE o.addres = :addres")})
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bill")
    private double bill;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "addres")
    private String addres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oRDERid")
    private Collection<Item> itemCollection;
    @JoinColumn(name = "RESTAURANT_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Restaurant rESTAURANTid;
    @JoinColumn(name = "USER_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User uSERid;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer id, Date date, String state, double bill, String addres) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.bill = bill;
        this.addres = addres;
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

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public Restaurant getRESTAURANTid() {
        return rESTAURANTid;
    }

    public void setRESTAURANTid(Restaurant rESTAURANTid) {
        this.rESTAURANTid = rESTAURANTid;
    }

    public User getUSERid() {
        return uSERid;
    }

    public void setUSERid(User uSERid) {
        this.uSERid = uSERid;
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
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ea.entity.Order[ id=" + id + " ]";
    }
    
}
