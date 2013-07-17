package ru.t_systems.demail.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.t_systems.demail.model.message.Label;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.UserService;
import ru.t_systems.demail.web.util.DateUtility;

@Named
@SessionScoped
public class LoginController implements Serializable {

	@EJB
	private UserService service;

	private List<Label> labels;
	private User user;

	@Inject
	private transient Logger logger;
	private String username;
	private String password;

	public LoginController() {

	}

	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public void login(ActionEvent actionEvent) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		user = service.getUser("dart");
		context.getExternalContext().getSessionMap().put("user", user);
		//System.out.println("------------- ACCOUNT SIZE"+user.getAccounts().size());
		labels = new ArrayList<Label>();
		for (Account account : user.getAccounts()) {
			labels.addAll(account.getLabel());
		//	System.out.println("------------- Label SIZE"+account.getLabel().size());
		}

		context.getExternalContext().getSessionMap().put("labels", labels);

		//user.setEmail("123@qwe886.ru");
	//	service.update(user);

		System.out.println("------User login = "
				+ service.getUser("admin").getEmail());
		try {
			String navigateString = "";
			// Checks if username and password are valid if not throws a
			// ServletException
			request.login(username, password);
			// gets the user principle and navigates to the appropriate page
			Principal principal = request.getUserPrincipal();
			if (request.isUserInRole("admin")) {
				navigateString = "/admin/AdminHome.xhtml";

			} else if (request.isUserInRole("user")) {
				navigateString = "/user/UserHome.xhtml";
			}
			try {
				logger.log(
						Level.INFO,
						"User ({0}) loging in #"
								+ DateUtility.getCurrentDateTime(), request
								.getUserPrincipal().getName());
				context.getExternalContext().redirect(
						request.getContextPath() + navigateString);
			} catch (IOException ex) {
				logger.log(Level.SEVERE, "IOException, Login Controller"
						+ "Username : " + principal.getName(), ex);
				context.addMessage(null, new FacesMessage("Error!",
						"Exception occured"));
			}
		} catch (ServletException e) {
			logger.log(Level.SEVERE, e.toString());
			context.addMessage(
					null,
					new FacesMessage("Error!",
							"The username or password you provided does not match our records."));
		}
	}

	
	public void logout() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		logger.log(Level.INFO,
				"User ({0}) loging out #" + DateUtility.getCurrentDateTime(),
				request.getUserPrincipal().getName());
		if (session != null) {
			session.invalidate();
		}
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						"/Login.xhtml?faces-redirect=true");
	}
}
