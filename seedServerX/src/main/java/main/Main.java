package main;

import entity.Place;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Place place = new Place();
        
        place.setName("Tisvildeleje Badehotel");
        place.setCity("Tisvildeleje");
        place.setStreet("Hovedgaden 75");
        place.setDescription("Oplev et ægte strandhotel nær stranden og oplev hvordan du straks mærker din krop falde til ro efter en tur på et badehotel i skønne Nordsjælland.");
        place.setUrl("http://strand-hotel.dk/hotel/");
        place.setGeo("56.0588077,12.0672844");
        place.setZip(3220);
        place.setRating(5);
        
        em.persist(place);
        em.getTransaction().commit();
        em.close();
        
    }

}
