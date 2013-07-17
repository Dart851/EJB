package ru.t_systems.demail.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.t_systems.demail.dao.account.AccountDAO;
import ru.t_systems.demail.model.user.Account;


@Stateless
public class AccountServiceImpl implements AccountService {

    @EJB
    private AccountDAO accountDAO;

    public Account getAccountByName(String login) {

        return accountDAO.getAccountByname(login);
    }

    public Account getAccountById(int id) {

        return accountDAO.getAccount(id);
    }


}
