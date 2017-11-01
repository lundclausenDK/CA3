package facades;

import entity.Role;
import javax.persistence.EntityManagerFactory;

interface IRoleFacade 
{
    boolean createRole(Role role);
    boolean removeRole(Role role);
    Role findRole(Role role);
    void addEntityManagerFactory(EntityManagerFactory emf);
}
