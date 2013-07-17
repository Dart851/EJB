package ru.t_systems.demail.web.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.RoleService;
import ru.t_systems.demail.service.UserService;

@Named
@RequestScoped
public class RegistrationController {

	@Inject
	RegistrationForm registrationForm;

	@EJB
	private UserService service;

	@EJB
	private RoleService roleService;

	public void registration(ActionEvent actionEvent) {

		// FacesContext context = FacesContext.getCurrentInstance();
		// HttpServletRequest request = (HttpServletRequest) context
		// .getExternalContext().getRequest();
		// registrationForm = (RegistrationForm) request
		// .getAttribute("registrationForm");

		
		FacesMessage message = new FacesMessage();
		
		/*FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(mailBean.getButtonAction().getClientId(context), new FacesMessage(alert));*/
		
		
		User user = registrationForm.toUser();

		user.setRole(roleService.getRole(2));

		service.addUser(user);

		System.out.println("--------------- LOGIN "
				+ registrationForm.getLogin() + registrationForm.getPassword()
				+ registrationForm.getConfirmPassword()
				+ registrationForm.getEmail());
	}
	
	public void validateLogin(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String sValue = (String)value;

        

        if (service.getUser(sValue)!=null) {
            FacesMessage message = new FacesMessage();
            message.setDetail("User alredy registred");
            message.setSummary("User alredy registred");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
