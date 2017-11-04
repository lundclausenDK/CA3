package facades;

import entity.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class RoleFacade implements IRoleFacade {

    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public boolean createRole(Role role)
    {
        EntityManager em = emf.createEntityManager();

        if (findRole(role.getRoleName()) != null)
            return false;

        em.getTransaction().begin();

        em.persist(role);

        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public boolean removeRole(String roleName)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role findRole(String roleName)
    {
        EntityManager em = emf.createEntityManager();

        Role found = em.find(Role.class, roleName);

        if (found != null)
        {
            return found;
        }

        return null;
    }

    @Override
    public List<Role> listAllRoles()
    {
        EntityManager em = emf.createEntityManager();
        
        List<Role> res = em.createQuery("select r from USER_ROLE r").getResultList();
        em.close();
        
        return res;
    }

}
