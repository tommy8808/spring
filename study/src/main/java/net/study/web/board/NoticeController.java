package net.study.web.board;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import net.study.dao.board.NoticeDao;
import net.study.domain.board.Notice;

@Controller
@RequestMapping("/board")
public class NoticeController {
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	@Resource(name="noticeDao")
	private NoticeDao noticeDao;
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/listNotice", method=RequestMethod.GET)
	public String listNotice(Notice notice, Model model){
		List<Notice> listNotice = noticeDao.listNotice(notice);
		notice = noticeDao.selectNotice(1);
		log.info("notice {} ", notice);
		for(Notice n : listNotice){
			log.info("Notice = {}", n);
		}
		model.addAttribute("listNotice", listNotice);
		return "board/notice/listNotice";
	}
	
	@RequestMapping(value="/createNotice", method=RequestMethod.GET)
	public String createNoticeForm(Notice notice){
		return "board/notice/noticeForm";
	}
	
	@RequestMapping(value="/createNotice", method=RequestMethod.POST)
	public String createNotice(Notice notice, MultipartFile file) throws Exception{
		String savedName = file.getOriginalFilename();
		savedName = uploadFile(savedName, file.getBytes());
		notice.setAtchflNm(savedName);
		noticeDao.createNotice(notice);
		return "redirect:/board/listNotice";
	}
	
	String uploadFile(String originalName, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
