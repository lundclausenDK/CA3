package facades;

import entity.Home;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IHomeFacade 
{
    public void addEntityManagerFactory(EntityManagerFactory emf);
    
    public List<Home> listAllHomes();
    public List<Home> findHomesCloseTo(String geolocation);
    public Home findHomeById(int id);
    public boolean rentHome(int id);
}
