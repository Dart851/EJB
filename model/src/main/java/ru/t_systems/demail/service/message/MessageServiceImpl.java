package ru.t_systems.demail.service.message;

import ru.t_systems.demail.dao.message.MessageDAO;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MessageServiceImpl implements MessageService {

    @EJB
    private MessageDAO messageDAO;

    public Message getMessage(int id) {
        return messageDAO.getMessage(id);
    }

    public void addMessage(Message message) {
        messageDAO.addMessage(message);

    }

    public List<Message> getMessageByStatus(List<MessageStatuss> status) {
        messageDAO.getMessageByStatus(status);
        return null;
    }

    public List<Message> getMessageByStatus(MessageStatuss status) {
        messageDAO.getMessageByStatus(Arrays.asList(status));
        return null;
    }

}
