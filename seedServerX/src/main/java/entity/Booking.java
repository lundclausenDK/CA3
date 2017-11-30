package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Booking(long startTime, long endTime, User renter) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.renter = renter;
    }

    public Booking() {
    }
   
    
    private long startTime;
    private long endTime;
    
    @OneToOne
    private User renter;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (this.getStartTime() != other.getStartTime()) {
            return false;
        }
        if (this.getEndTime() != other.getEndTime()) {
            return false;
        }
        if (!Objects.equals(this.renter, other.renter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Booking[ id=" + id + " ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setRenter(User user)
    {
        renter = user;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * @return the renter
     */
    public User getRenter() {
        return renter;
    }

}
