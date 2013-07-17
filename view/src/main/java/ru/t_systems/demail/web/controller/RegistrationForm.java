package ru.t_systems.demail.web.controller;

import java.io.Serializable;
import java.util.Date;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ru.t_systems.demail.model.user.User;

@Named (value="registrationForm")
@RequestScoped
public class RegistrationForm implements Serializable {
	private String login;
	@NotNull
	@Size(min = 4, max = 20)
	private String password;
	@NotNull
	private String confirmPassword;
	private String email;
	private String fistName;
	private String lastName;
	private String gender;
	private Date birthDay;

	
	public User toUser(){
		User user = new User();
		user.setLogin(this.getLogin());
		user.setPassword(this.getPassword());
		user.setLastName(this.getLastName());
		user.setFirstName(this.getFistName());
		user.setEmail(this.getEmail());
		user.setSex(this.getGender());
		user.setBirthDay(this.getBirthDay());
		return user;
		
	}
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
