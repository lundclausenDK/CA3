package facades;

import entity.Place;
import java.util.List;
import javax.persistence.EntityManagerFactory;

interface IPlaceFacade 
{
    void addEntityManagerFactory(EntityManagerFactory emf);
    boolean createPlace(Place place);
    boolean removePlace(int id);
    Place findPlace(int id);
    List<Place> listAllPlaces();
    List<Place> searchForPlaces(String searchWord);
}
