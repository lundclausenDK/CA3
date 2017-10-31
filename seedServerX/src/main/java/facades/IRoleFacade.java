package facades;

import entity.Role;
import javax.persistence.EntityManagerFactory;

public interface IRoleFacade 
{
    Role createRole(Role role);
    boolean removeRole(Role role);
    void addEntityManagerFactory(EntityManagerFactory emf);
}
