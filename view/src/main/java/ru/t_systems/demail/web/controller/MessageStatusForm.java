package ru.t_systems.demail.web.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ru.t_systems.demail.model.message.DeletStatus;
import ru.t_systems.demail.model.message.Label;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.message.MessageStatusService;

@Named(value = "messageStatusForm")
@RequestScoped
public class MessageStatusForm implements Serializable {

	private List<MessageStatuss> statusList;

	public List<MessageStatuss> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<MessageStatuss> statusList) {
		Collections.reverse(statusList);
		this.statusList = statusList;
	}

	@EJB
	private MessageStatusService messageStatusService;

	@PostConstruct
	private void getMessage() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		System.out.println("---- get message status in form");
		List<MessageStatuss> statusList = messageStatusService.getInputStatus(
				1, 50, 0);
		
		this.setStatusList(statusList);
	}

}
