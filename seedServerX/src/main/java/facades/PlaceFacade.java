package facades;

import entity.Place;
import entity.Rating;
import entity.User;
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
    public Place findPlace(String locationName)
    {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("select p from Place p where p.name like :locname");
        query.setParameter("locname", locationName);
        Place found = (Place) query.getResultList().get(0);
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

        List<Place> res = query.getResultList();

        em.close();
        return res;
    }

    @Override
    public void addRating(int locationID, int rating, String userName)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Place place = em.find(Place.class, locationID);
        User user = (User) em.createQuery("select u from entity.User u where u.userName like :userName").setParameter("userName", userName).getSingleResult();
        List<Rating> ratings = place.getRatings();
        Rating newRating = new Rating();
        newRating.setRatingValue(rating);
        newRating.setUser(user);
        ratings.add(newRating);
        place.setRatings(ratings);

        em.getTransaction().commit();
        em.close();
    }

}
