package facades;

import entity.Place;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

class PlaceFacade implements IPlaceFacade {
    
    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
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
    }

    @Override
    public List<Place> searchForPlaces(String searchWord)
    {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("select p from Place p where p.city like :word or p.description like :word "
                + "or p.name like :word or p.street like :word");
        query.setParameter("word", "%" + searchWord + "%");
        System.out.println("toString: " + query.toString());
        
        List<Place> res = query.getResultList();
        
        em.close();
        return res;
    }

}
