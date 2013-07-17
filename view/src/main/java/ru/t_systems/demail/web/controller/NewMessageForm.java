package ru.t_systems.demail.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;

@ManagedBean(name = "newMessage")
@ViewScoped
public class NewMessageForm implements Serializable {

	public NewMessageForm() {

	}

	public void create(Message message) {
		setBody(message.getBody());
		setTitle(message.getTitle());
		setStatus(message.getStatus());
	}

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String body;
	private Date timeStamp = new Date();
	private List<MessageStatuss> status;
	private String title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<MessageStatuss> getStatus() {
		return status;
	}

	public void setStatus(List<MessageStatuss> status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Message toMessage() {
		Message message = new Message();
		message.setBody(this.getBody());
		message.setTimeStamp(this.getTimeStamp());
		message.setTitle(this.getTitle());
		message.setStatus(this.getStatus());
		return message;
	}
}
