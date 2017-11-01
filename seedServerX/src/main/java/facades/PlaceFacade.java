package facades;

import entity.Place;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class PlaceFacade implements IPlaceFacade {
    
    private EntityManagerFactory emf;

    private EntityManagerFactory emf;
    
    @Override
<<<<<<< HEAD
    public void addEntityManagerFactory(EntityManagerFactory emf) {
=======
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
>>>>>>> 95760327a94ecea0cf5351955c3e7af41bb71fe7
        this.emf = emf;
    }

    @Override
<<<<<<< HEAD
    public boolean createPlace(Place place) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removePlace(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Place findPlace(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Place> listAllPlaces() {
        EntityManager em = emf.createEntityManager();
        List<Place> results = em.createQuery("SELECT p FROM Place p").getResultList();
        return results;
=======
    public boolean createPlace(Place place)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(place);
        
        em.getTransaction().commit();
        em.close();
        
        return true;
    }

    @Override
    public boolean removePlace(int id)
    {
        EntityManager em = emf.createEntityManager();
        boolean removed = false;
        em.getTransaction().begin();
        
        Place found = findPlace(id);
        if (found != null)
        {
            em.remove(found);
            removed = true;
        }
        
        em.getTransaction().commit();
        em.close();
        
        return removed;
    }

    @Override
    public Place findPlace(int id)
    {
        EntityManager em = emf.createEntityManager();
        
        Place found = em.find(Place.class, id);
        em.close();
        
        return found;
    }

    @Override
    public List<Place> listAllPlaces()
    {
        EntityManager em = emf.createEntityManager();
        
        List<Place> res = em.createQuery("select p from Place p").getResultList();
        em.close();
        
        return res;
>>>>>>> 95760327a94ecea0cf5351955c3e7af41bb71fe7
    }
    
    

}
