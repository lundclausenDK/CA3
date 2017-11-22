package main;

import entity.Place;
import entity.Rating;
import entity.Role;
import entity.User;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordStorage;

public class Main {
    public static void main(String[] args) throws PasswordStorage.CannotPerformOperationException {
        /*
        Place place01 = new Place();
        Place place02 = new Place();
        Place place03 = new Place();
        
        place01.setName("Tisvildeleje Badehotel");
        place01.setCity("Tisvildeleje");
        place01.setStreet("Hovedgaden 75");
        place01.setDescription("Oplev et ægte strandhotel nær stranden og oplev hvordan du straks mærker din krop falde til ro efter en tur på et badehotel i skønne Nordsjælland.");
        place01.setUrl("http://1.bp.blogspot.com/-qpnAWteLG_M/TqGwVjD1EjI/AAAAAAAAAeM/kdoJnUEpDfM/s1600/27196_10150160087310441_90249275440_11697993_2143765_n.jpg");
        place01.setGeo("56.0588077,12.0672844");
        place01.setZip(3220);
        
        place02.setName("Skjoldenæsholm");
        place02.setCity("Jystrup");
        place02.setStreet("Skjoldenæsvej 106");
        place02.setDescription("Den smukke Herregård Skjoldenæsholm er beliggende midt på Sjælland i naturskønne omgivelser og er indbegrebet af et dansk herregårdshotel.");
        place02.setUrl("https://s-ec.bstatic.com/images/hotel/max1024x768/606/60654441.jpg");
        place02.setGeo("55.604444,12.088299");
        place02.setZip(4174);
        
        place03.setName("Norsminde Kro");
        place03.setCity("Odder");
        place03.setStreet("Gammel Krovej 2");
        place03.setDescription("Brasserie med udsigt til havnen. Gourmetrestaurant i den store vinkælder 27 charmerende og individuelt designede værelser Flotte, lyse selskabslokaler op til 140 personer.");
        place03.setUrl("https://t-ec.bstatic.com/images/hotel/max1024x768/265/26577734.jpg");
        place03.setGeo("56.021966,10.2598323");
        place03.setZip(8300);
        
        ICollectiveFacade facade = CollectiveFacadeFactory.getInstance();
        
        Role userRole = new Role("User");
        Role adminRole = new Role("Admin");
        
        User user = new User("user", "test");
        User admin = new User("admin", "test");
        user.addRole(userRole);
        admin.addRole(adminRole);
        
        facade.createRole(userRole);
        facade.createRole(adminRole);
        facade.registerUser(user);
        facade.registerUser(admin);
        
        facade.createPlace(place01);
        facade.createPlace(place02);
        facade.createPlace(place03);
        */
        
        ICollectiveFacade facade = CollectiveFacadeFactory.getInstance();
        
        Place place = facade.findPlaceByName("Skjoldenæsholm");
        
        System.out.println(place.getName());
    }

}
