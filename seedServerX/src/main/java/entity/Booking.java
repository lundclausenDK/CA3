package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
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
        if (this.startTime != other.startTime) {
            return false;
        }
        if (this.endTime != other.endTime) {
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

}
