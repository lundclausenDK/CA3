package facades;

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
    public List<Home> listAllHomes()
    {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("select h from Home h");
        List<Home> homes = query.getResultList();
        em.close();
        
        return homes;
    }

    @Override
    public List<Home> findHomesCloseTo(String geolocation)
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
    public boolean rentHome(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
