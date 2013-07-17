package ru.t_systems.demail.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.t_systems.demail.dao.user.RoleDAO;
import ru.t_systems.demail.model.user.Role;


@Stateless
public class RoleServiceImpl implements RoleService {

    @EJB
    private RoleDAO roleDAO;

    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }

    public void saveRole(Role role) {
        roleDAO.saveRole(role);

    }

}
