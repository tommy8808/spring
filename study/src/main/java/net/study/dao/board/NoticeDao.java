package net.study.dao.board;

import java.util.List;

import net.study.domain.board.Notice;

public interface NoticeDao {
	
	List<Notice> listNotice(Notice notice);
	
	Notice selectNotice(int bno);
	
	void createNotice(Notice notice);
	
	void updateNotice(Notice notice);
	
	void deleteNotice(int bno);
}
