package ru.t_systems.demail.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ru.t_systems.demail.dao.message.MessageStatusDAO;
import ru.t_systems.demail.model.message.DeletStatus;
import ru.t_systems.demail.model.message.Label;
import ru.t_systems.demail.model.message.MessageStatuss;

@ManagedBean
@ViewScoped
public class OpenMailController implements Serializable {

	@EJB
	private MessageStatusDAO statusDAO;

	@ManagedProperty(value = "#{newMessage}")
	private NewMessageForm newMessage;

	public NewMessageForm getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(NewMessageForm newMessage) {
		this.newMessage = newMessage;
	}

	private String id;

	private MessageStatuss messageStatuss;

	private Set<Label> labels;

	private Label label;

	@PostConstruct
	public void init() throws IOException {

		this.id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("statusId");

		Integer id = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			id = Integer.parseInt(this.id);
			messageStatuss = statusDAO.getMessageStatus(id);
			labels = messageStatuss.getAccount().getLabel();
			label = messageStatuss.getLabel();
			messageStatuss.setIsRead(true);
			statusDAO.update(messageStatuss);

		} catch (Exception e) {

		}

		if (id == null) {
			System.out.println("---------- REDIRECT");
			context.getExternalContext().redirect(
					request.getContextPath() + "/user/NewMessage.xhtml");
		}

	}

	public void toFolder() {
		messageStatuss.setLabel(label);
		statusDAO.update(messageStatuss);
	}

	public void spam() {
		if (!messageStatuss.getIsSpam()) {
			messageStatuss.setIsSpam(true);
		} else {
			messageStatuss.setIsSpam(false);
		}
		statusDAO.update(messageStatuss);
	}

	public void delete() {
		if (messageStatuss.getIsDelet().equals(DeletStatus.NOT)) {
			messageStatuss.setIsDelet(DeletStatus.TRASH);
		} else if (messageStatuss.getIsDelet().equals(DeletStatus.TRASH)) {
			messageStatuss.setIsDelet(DeletStatus.DELET);
		}

		statusDAO.update(messageStatuss);
	}

	public String reply() throws IOException {
		return "NewMessage";

	}
	
	public String box() {
		return "UserHome";

	}

	public MessageStatuss getMessageStatuss() {
		return messageStatuss;
	}

	public void setMessageStatuss(MessageStatuss messageStatuss) {
		this.messageStatuss = messageStatuss;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
