package ru.t_systems.demail.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.UserService;
import ru.t_systems.demail.service.message.MessageStatusService;

@ManagedBean
@SessionScoped
public class MailBoxController {

	/*
	 * @Inject private MessageStatusForm messageStatusForm;
	 */
	private static final Integer limit = 10;

	private String box;

	private Integer numberPage;

	public Integer getNumberPage() {
		return numberPage;
	}

	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	private List<MessageStatuss> statusList;

	@EJB
	private UserService service;

	@EJB
	private MessageStatusService messageStatusService;

	List<Integer> accountsId;
	private Integer countInput = 0;
	private Integer countAllInput;

	@PostConstruct
	public void postConstract() {
		System.out.println("--------------------POST CONSTRUCTOR MAIL BOX");
		List<Account> accounts = new ArrayList<Account>(((User) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("user")).getAccounts());

		accountsId = new ArrayList<Integer>();
		for (Account account : accounts) {
			accountsId.add(account.getId());
		}
		setCountAllInput(messageStatusService.getInputStatusCount(accountsId));
		// updateCount();
		countAllInput = messageStatusService.getInputStatusCount(accountsId);

	}

	public void init() {
		

		getMessage();

	}

	public void getMessage() {

		
		System.out.println("--------------------PRE RENDER MAIL BOX");
		if (numberPage == null) {
			numberPage = 0;
		}

		if (box == null) {
			setBox("input");
		}
		
		if (box.equals("input")) {
			getInput();
		} else if (box.equals("output")) {
			getOutput();
		} else if (box.equals("spam")) {
			getSpam();
		} else if (box.equals("trash")) {
			getTrash();
		}
		// updateCount();
	}

	// private void updateCount() {
	// countInput = messageStatusService
	// .getInputStatusCountNotRead(accountsId);
	// }

	public void getInput() {
		setStatusList(messageStatusService.getInputStatus(1, limit, limit
				* numberPage));

		setBox("input");
	}

	public void getOutput() {
		setStatusList(messageStatusService.getOutputStatus(1, limit, limit
				* numberPage));
		setBox("output");
	}

	public void getSpam() {
		setStatusList(messageStatusService.getSpamStatus(1, limit, limit
				* numberPage));
		setBox("spam");
	}

	public void getTrash() {
		setStatusList(messageStatusService.getTrashStatus(1, limit, limit
				* numberPage));
		setBox("trash");
	}

	public void openMessage() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			context.getExternalContext().redirect(
					request.getContextPath()
							+ "/user/OpenMessage.xhtml?statusId="
							+ request.getParameter("statusId"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<MessageStatuss> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<MessageStatuss> statusList) {
		this.statusList = statusList;
	}

	public Integer getCountInput() {
		return countInput;
	}

	public void setCountInput(Integer countInput) {
		this.countInput = countInput;
	}

	public Integer getCountAllInput() {
		return countAllInput;
	}

	public void setCountAllInput(Integer countAllInput) {
		this.countAllInput = countAllInput;
	}

}
