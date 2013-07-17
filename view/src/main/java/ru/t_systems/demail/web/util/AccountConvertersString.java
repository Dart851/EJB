package ru.t_systems.demail.web.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.service.AccountService;

@ManagedBean(name = "accountConverterString")
public class AccountConvertersString implements Converter {

	@EJB
	private AccountService accountService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Set<String> acount = new HashSet<String>(
				Arrays.asList(value.split(";")));

		Set<Account> set = new HashSet<Account>();

		for (String name : acount) {
			Account acc = accountService.getAccountByName(name.trim());
			if (acc != null) {
				set.add(acc);
			} else {
				Account account = new Account();
				account.setAccountName(name);
				account.setUser(null);
				set.add(account);
			}
		}

		System.out.println("Send to Acount n " + set.size());
		if (set.size() > 0) {
			return set;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {

			if (value instanceof String) {
				return (String) value;
			} else {

				String out = new String();

				for (Account account : (Set<Account>) value) {
					out = out + account.getAccountName() + ";";
				}

				return out;
			}
		} else {
			return "";
		}
	}
}