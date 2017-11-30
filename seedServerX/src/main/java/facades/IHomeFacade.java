package facades;

import entity.Booking;
import entity.Home;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IHomeFacade 
{
    public void addEntityManagerFactory(EntityManagerFactory emf);
    
    public void addHome(Home home);
    public List<Home> listAllHomes();
    public Home findHomeById(int id);
    public boolean rentHome(int id, Booking booking);
}
