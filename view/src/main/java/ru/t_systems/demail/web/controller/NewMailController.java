package ru.t_systems.demail.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.AccountService;
import ru.t_systems.demail.service.UserService;
import ru.t_systems.demail.service.message.MessageService;
import ru.t_systems.demail.service.message.MessageStatusService;
import ru.t_systems.demail.web.util.HTMLValidator;

@ManagedBean
@RequestScoped
public class NewMailController implements Serializable {

	@ManagedProperty(value = "#{newMessage}")
	private NewMessageForm newMessage;

	@ManagedProperty(value = "#{param.replyId}")
	private Integer statusId;

	private MessageStatuss messageStatuss;

	public NewMessageForm getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(NewMessageForm newMessage) {
		this.newMessage = newMessage;
	}

	@EJB
	private MessageStatusService messageStatusService;

	@EJB
	private MessageService messageService;

	// @EJB
	// private UserService userService;

	@EJB
	private AccountService accountService;

	private Set<Account> accounts;

	private Account accountSender;

	private Set<Account> sendAccount;

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void init() {
		System.out.println("----POST CONSTRUCT NEW MAIL" + statusId);

		if (statusId != null) {
			messageStatuss = messageStatusService.getMessageStatus(statusId);
			newMessage.create(messageStatuss.getMessage());
			accountSender = messageStatuss.getAccount();
			sendAccount = new HashSet<Account>();
			sendAccount.add(messageStatuss.getAcountsSender());
		}

		FacesContext context = FacesContext.getCurrentInstance();

		setAccounts(((User) context.getExternalContext().getSessionMap()
				.get("user")).getAccounts());
	}

	public void sendMessage() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		//HTMLValidator validator = new HTMLValidator();
		System.out.println("-----OLD BODY "+newMessage.getBody());
		System.out.println("-----BODY "+HTMLValidator.validate(newMessage.getBody()));
		newMessage.setBody(HTMLValidator.validate(newMessage.getBody()));
		Message message = newMessage.toMessage();

		System.out.println("Set to send " + sendAccount);

		List<MessageStatuss> list = new ArrayList<MessageStatuss>();
		List<MessageStatuss> listError = new ArrayList<MessageStatuss>();

		for (Account toAcc : sendAccount) {
			MessageStatuss status = new MessageStatuss();
			status.setAccount(toAcc);
			status.setAcountsSender(accountSender);
			if (toAcc.getUser() == null) {
				System.out.println("--------------FAIL ACCOUNT");
				listError.add(status);
			} else {

				list.add(status);
			}

		}
		if (list.size() > 0) {
			message.setBody("<br><br><blockquote>" + message.getBody()
					+ "</blockquote>");
			message.setStatus(list);
			messageService.addMessage(message);
		}
		if (listError.size() > 0) {
			Message messageError = newMessage.toMessage();
			messageError.setTitle("Invalid Email: " + messageError.getTitle());
			StringBuffer buffer = new StringBuffer();
			buffer.append("Invalid Email: ");
			for (MessageStatuss messageStatuss : listError) {
				buffer.append(messageStatuss.getAccount().getAccountName());
				buffer.append("; ");
			}
			messageError.setBody(buffer.toString() + "<blockquote>"
					+ messageError.getBody() + "</blockquote>");
			MessageStatuss messageStatuss = new MessageStatuss();
			messageStatuss.setAccount(accountSender);
			messageStatuss.setAcountsSender(accountService
					.getAccountByName("mailer-deamon@de-mail.de"));

			messageError.setStatus(Arrays.asList(messageStatuss));
			// messageError.setStatus(listError);
			messageService.addMessage(messageError);
		}

		context.getExternalContext().redirect(
				request.getContextPath() + "/user/UserHome.xhtml");
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Account> getSendAccount() {
		return sendAccount;
	}

	public void setSendAccount(Set<Account> sendAccount) {
		this.sendAccount = sendAccount;
	}

	public Account getAccountSender() {
		return accountSender;
	}

	public void setAccountSender(Account accountSender) {
		this.accountSender = accountSender;
	}

	public MessageStatuss getMessageStatuss() {
		return messageStatuss;
	}

	public void setMessageStatuss(MessageStatuss messageStatuss) {
		this.messageStatuss = messageStatuss;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

}
