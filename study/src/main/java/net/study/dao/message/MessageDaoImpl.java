package net.study.dao.message;

import org.apache.ibatis.session.SqlSession;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.study.domain.message.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void create(Message dto) {
		sqlSession.insert("message.create", dto);
	}

	@Override
	public Message readMessage(int mid) {
		return null;
	}

	@Override
	public void updateState(int mid) {

	}

}
