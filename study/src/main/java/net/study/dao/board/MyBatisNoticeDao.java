package net.study.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.study.domain.board.Notice;

@Repository("noticeDao")
public class MyBatisNoticeDao implements NoticeDao {
	@Autowired
	private SqlSession sqlSession;
	
	/*public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}*/

	@Override
	public List<Notice> listNotice(Notice notice) {
		return sqlSession.selectList("NoticeMapper.listNotice", notice);
	}

	@Override
	public Notice selectNotice(int bno) {
		return sqlSession.selectOne("NoticeMapper.selectNotice", bno);
	}

	@Override
	public void createNotice(Notice notice) {
		sqlSession.insert("NoticeMapper.insertNotice", notice);
	}

	@Override
	public void updateNotice(Notice notice) {
		sqlSession.update("NoticeMapper.updateNotice", notice);
	}

	@Override
	public void deleteNotice(int bno) {
		sqlSession.delete("NoticeMapper.deleteNotice", bno);
	}

}
