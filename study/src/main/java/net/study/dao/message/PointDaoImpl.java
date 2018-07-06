package net.study.dao.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDaoImpl implements PointDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void updatePoint(String userId, int point) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("point", point);
		sqlSession.update("point.updatePoint", map);
	}

}
