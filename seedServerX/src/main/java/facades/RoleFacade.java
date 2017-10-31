package facades;

import entity.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RoleFacade implements IRoleFacade
{
    private EntityManagerFactory emf;

    @Override
    public Role createRole(Role role)
    {
        EntityManager em = emf.createEntityManager();
        
        em.persist(role);
        Role found = em.find(Role.class, em);
        
        return found;
    }

    @Override
    public boolean removeRole(Role role)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

}