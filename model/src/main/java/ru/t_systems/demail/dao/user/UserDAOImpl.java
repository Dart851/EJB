package ru.t_systems.demail.dao.user;

import ru.t_systems.demail.model.user.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAOImpl extends AbstractDAO implements UserDAO {

	@SuppressWarnings("unchecked")
	public User getUser(String login) {
		List<User> userList = new ArrayList<User>();
		Query query = getCurrentSession().createQuery(
				"from User u where u.login = :login");
		query.setParameter("login", login);
		userList = query.getResultList();

		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;

		// return getCurrentSession().find(User.class, 1);
	}

	public void addUser(User user) {
		getCurrentSession().merge(user);

	}

	public void update(User user) {
		getCurrentSession().merge(user);

	}

	public void updatea(User user) {
		updatea(user);

	}

	public void deleteUser(User user) {
		getCurrentSession().remove(user);

	}

}
