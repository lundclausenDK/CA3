package facades;

import entity.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RoleFacade implements IRoleFacade
{
    private EntityManagerFactory emf;

    @Override
    public boolean createRole(Role role)
    {
        EntityManager em = emf.createEntityManager();
        
        if (findRole(role) != null)
            return false;
        
        em.getTransaction().begin();
        
        em.persist(role);
        
        em.getTransaction().commit();
        em.close();
        
        return true;
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

    @Override
    public Role findRole(Role role)
    {
        EntityManager em = emf.createEntityManager();
        
        Role found = em.find(Role.class, role.getRoleName());
        
        if (found != null)
        {
            return found;
        }
        
        return null;
    }

}