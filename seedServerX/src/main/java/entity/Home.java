/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Bade
 */
@Entity
public class Home implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String address;
    private int zipcode;
    private String city;
    private String geo;
    private String picture;
    private double price;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Booking> bookings = new ArrayList();

    public Home() {}

    public Home(String name, String description, String address, int zipcode, String city, String geo, double price, String picture) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.geo = geo;
        this.price = price;
        this.picture = picture;
    }

    public Home(int id, String name, String description, String address, int zipcode, String city, String geo, double price, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.geo = geo;
        this.price = price;
        this.bookings = bookings;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Home)) {
            return false;
        }
        Home other = (Home) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Home[ id=" + id + " ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getGeo() {
        return geo;
    }

    public double getPrice() {
        return price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }
    
    

}
