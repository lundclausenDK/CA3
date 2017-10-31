package facades;

import entity.Role;

interface IRoleFacade 
{
    boolean createRole(Role role);
    boolean removeRole(Role role);
}
