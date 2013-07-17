package ru.t_systems.demail.dao.user;

import javax.ejb.Stateless;

import ru.t_systems.demail.model.user.Role;

@Stateless
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {


	public Role getRole(int id) {

		return (Role) getCurrentSession().find(Role.class, id);
	}

	public void saveRole(Role role) {
		getCurrentSession().persist(role);

	}

}
