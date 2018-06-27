package net.study.dao.users;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.study.domain.users.User;

@Repository("userDao")
public class MyBatisUserDao implements UserDao {
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public User findById(String userId) {
		return (User)sqlSession.selectOne("UserMapper.findById", userId);
	}

	@Override
	public void create(User user) {
		sqlSession.insert("UserMapper.create", user);
	}

	@Override
	public void update(User user) {
		sqlSession.update("UserMapper.update", user);
	}

	@Override
	public void delete(String userId) {
		sqlSession.delete(userId);
	}

}
