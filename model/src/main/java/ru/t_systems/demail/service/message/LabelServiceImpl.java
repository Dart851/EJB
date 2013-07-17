package ru.t_systems.demail.service.message;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.t_systems.demail.dao.message.LabelDAO;
import ru.t_systems.demail.model.message.Label;

@Stateless
public class LabelServiceImpl implements LabelService {

    @EJB
    private LabelDAO labelDAO;

    public Label getLabel(int id) {
        return labelDAO.getLabel(id);
    }

    public void addLabel(Label label) {
    	labelDAO.addLabel(label);

    }

	public void update(Label label) {
		labelDAO.update(label);
		
	}

	   

}
