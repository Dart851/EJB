package ru.t_systems.demail.web.validation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import ru.t_systems.demail.service.AccountService;
import ru.t_systems.demail.service.UserService;

@ManagedBean
public class LoginValidation implements Validator {

	
	@EJB
	private UserService userService;


	@EJB
	private AccountService accountService;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		/*System.out.println("User service = " + userService.getUser("admin"));
		System.out.println("Account service = "
				+ accountService.getAccountByName("admin"));*/

		FacesMessage msg = new FacesMessage("Login validation failed.",
				"User alredy registred.");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(msg);

	}
}
