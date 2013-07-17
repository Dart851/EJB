package ru.t_systems.demail.dao.account;


import ru.t_systems.demail.dao.user.AbstractDAO;
import ru.t_systems.demail.model.user.Account;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AccountDAOImpl extends AbstractDAO implements AccountDAO {

	
	public Account getAccount(int id) {
		Account account = getCurrentSession().find(Account.class, id);
		if (account != null) {
			return account;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Account getAccountByname(String account) {
		List<Account> accountList = new ArrayList<Account>();
		Query query = getCurrentSession().createQuery(
				"from Account a where a.accountName = :account");
		query.setParameter("account", account);
		accountList = query.getResultList();
		if (accountList.size() > 0)
			return accountList.get(0);
		else

			return null;
	}

}
