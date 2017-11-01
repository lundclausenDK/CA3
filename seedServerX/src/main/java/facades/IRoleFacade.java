package facades;

import entity.Role;
import javax.persistence.EntityManagerFactory;

interface IRoleFacade 
{
    void addEntityManagerFactory(EntityManagerFactory emf);
    boolean createRole(Role role);
    boolean removeRole(String roleName);
    Role findRole(String roleName);
}
