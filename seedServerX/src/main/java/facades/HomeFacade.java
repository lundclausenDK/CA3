package facades;

import entity.Booking;
import entity.Home;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

class HomeFacade implements IHomeFacade {

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
        boolean success = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select b from BOOKING b join HOME_BOOKING e where Home_ID like " + id);
        query.setParameter("locname", id);
        List<Booking> bookings = query.getResultList();
        Home found = em.find(Home.class, id);
        
        long start = booking.getStartTime();
        long end = booking.getEndTime();
        if (found != null)
        { 
            for(Booking bookingTemp: bookings){
                if((start > bookingTemp.getStartTime() && start < bookingTemp.getEndTime()) || (end > bookingTemp.getStartTime() && end < bookingTemp.getEndTime())){
                    System.out.println("TEST !!!!!!!!!!!!!!!!!!!!!");
                    return false;
                }
            }
            success = true;
            found.addBooking(booking);
            em.persist(booking);
        }
        
        em.getTransaction().commit();
        em.close();

        return success;
    }

}
