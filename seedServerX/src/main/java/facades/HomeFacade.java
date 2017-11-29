package facades;

import entity.Booking;
import entity.Home;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class HomeFacade implements IHomeFacade
{
    private EntityManagerFactory emf;
    
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public void addHome(Home home)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(home);
        em.getTransaction().commit();
        em.close();
        
    }
    
    @Override
    public List<Home> listAllHomes()
    {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("select h from Home h");
        List<Home> homes = query.getResultList();
        em.close();
        
        return homes;
    }

    @Override
    public List<Home> findHomesCloseTo(String geolocation, double radius)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Home findHomeById(int id)
    {
        EntityManager em = emf.createEntityManager();
        
        Home found = em.find(Home.class, id);
        em.close();
        
        return found;
    }

    @Override
    public boolean rentHome(int id, Booking booking)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
