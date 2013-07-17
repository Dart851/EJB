package ru.t_systems.demail.web.util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.model.user.Account;

@ManagedBean(name = "accountConverter")
public class AccountConverters implements Converter {

	@EJB
	private AccountDAO accountDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			return accountDAO.getAccount(Integer.parseInt(value));
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {

			return ((Account) value).getId().toString();
		} else {
			return "";
		}
	}
}