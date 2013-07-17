package ru.t_systems.demail.service;

import ru.t_systems.demail.dao.user.CountryDAO;
import ru.t_systems.demail.model.user.Country;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class CountryServiceImpl implements CountryService {

    @EJB
    private CountryDAO countryDAO;

  
    public Country getCountry(int id) {
        return countryDAO.getCountry(id);
    }

   
    public List<Country> getAllCountry() {
        return countryDAO.getAllCountry();
    }

    
    public Country getCountry(String name) {
        return countryDAO.getCountry(name);

    }

}
