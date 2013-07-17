package ru.t_systems.demail.web.util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.t_systems.demail.dao.message.LabelDAO;
import ru.t_systems.demail.model.message.Label;

@ManagedBean(name = "labelConverter")
public class LabelConverters implements Converter {

	@EJB
	private LabelDAO labelDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			return labelDAO.getLabel(Integer.parseInt(value));
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {

			return ((Label) value).getId().toString();
		} else {
			return "";
		}
	}
}