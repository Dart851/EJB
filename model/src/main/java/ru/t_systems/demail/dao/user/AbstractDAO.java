package ru.t_systems.demail.dao.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDAO {

	@PersistenceContext(name = "demail")
	private EntityManager entityManager;

	protected EntityManager getCurrentSession() {
		return entityManager;
	}

	public <T> void updatea(T model) {
		entityManager.merge(model);

	}

	public <T> void delete(T model) {
		entityManager.remove(model);

	}

	public <T> void create(T model) {
		entityManager.merge(model);

	}

	@SuppressWarnings("unchecked")
	public <T> T read(T model) {
		return (T) entityManager.find(model.getClass(), model);

	}
}
