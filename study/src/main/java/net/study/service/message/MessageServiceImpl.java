package net.study.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.study.dao.message.MessageDao;
import net.study.dao.message.PointDao;
import net.study.domain.message.Message;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDao messageDao;
	@Autowired
	PointDao pointDao;
	static int point = 10;
	
	@Transactional //sql 실패시 롤백
	@Override
	public void addMessage(Message dto) {
		messageDao.create(dto);
		pointDao.updatePoint(dto.getSender(), point);
	}

	@Override
	public Message readMessage(String userId, int mid) {
		return null;
	}

}
