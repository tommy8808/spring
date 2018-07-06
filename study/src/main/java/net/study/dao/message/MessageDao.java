package net.study.dao.message;

import net.study.domain.message.Message;

public interface MessageDao {
	public void create(Message dto);
	public Message readMessage(int mid);
	public void updateState(int mid);
}
