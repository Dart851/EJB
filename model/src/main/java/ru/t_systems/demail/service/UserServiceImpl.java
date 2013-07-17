package ru.t_systems.demail.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ru.t_systems.demail.dao.user.UserDAO;
import ru.t_systems.demail.model.user.User;


@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserDAO userDAO;

    public void updateA(User user) {
        userDAO.update(user);
    }
    
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    public void addUser(User user) {
        userDAO.addUser(user);

    }

    public boolean isRegistred(String login) {
        return userDAO.getUser(login) != null;
    }

    public void update(User user) {
        userDAO.update(user);

    }

    @Deprecated
    public void deleteUser(User user) {
        userDAO.deleteUser(user);

    }

}
