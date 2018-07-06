package net.study.service.message;

import net.study.domain.message.Message;

public interface MessageService {
	public void addMessage(Message dto);
	public Message readMessage(String userId, int mid);
}
