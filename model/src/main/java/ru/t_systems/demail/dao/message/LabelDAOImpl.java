package ru.t_systems.demail.dao.message;

import javax.ejb.Stateless;

import ru.t_systems.demail.dao.user.AbstractDAO;
import ru.t_systems.demail.model.message.Label;

@Stateless
public class LabelDAOImpl extends AbstractDAO implements LabelDAO {

	public Label getLabel(int id) {

		return (Label) getCurrentSession().find(Label.class, id);

	}

	public void addLabel(Label label) {
		getCurrentSession().merge(label);

	}

	public void update(Label label) {
		getCurrentSession().merge(label);
		
	}

	

}
