package facades;

import entity.Place;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class PlaceFacade implements IPlaceFacade {
    
    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
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
    }
    
    

}
