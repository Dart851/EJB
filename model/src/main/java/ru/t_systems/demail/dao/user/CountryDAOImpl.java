package ru.t_systems.demail.dao.user;

import ru.t_systems.demail.model.user.Country;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CountryDAOImpl extends AbstractDAO implements CountryDAO {

	
	public Country getCountry(int id) {

		return (Country) getCurrentSession().find(Country.class, id);
	}

	@SuppressWarnings("unchecked")
	
	public List<Country> getAllCountry() {

		return getCurrentSession().createQuery("from Country").getResultList();

	}

	@SuppressWarnings("unchecked")
	
	public Country getCountry(String name) {
		List<Country> userList = new ArrayList<Country>();

		Query query = getCurrentSession().createQuery(
				"from Country u where u.id = :name");
		query.setParameter("name", Integer.valueOf(name));
		userList = query.getResultList();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

}
