package net.study.web.board;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String createNoticeForm(Notice notice, HttpSession session){
		//세션에 user 정보가 있는 지 확인
		String userId = (String) session.getAttribute("userId");
		if(userId == null){
			return "redirect:/users/login/form";
		}
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
	
	@RequestMapping(value="/selectNotice", method=RequestMethod.GET)
	public String selectNotice(Model model, int bno){
		Notice notice = noticeDao.selectNotice(bno);
		model.addAttribute("noticeVO", notice);
		return "board/notice/noticeDetail";
	}
	
	@RequestMapping(value="/updateNotice", method=RequestMethod.GET)
	public String updateNoticeForm(Model model, int bno){
		Notice notice = noticeDao.selectNotice(bno);
		model.addAttribute("notice", notice);
		return "board/notice/noticeForm";
	}
	
	@RequestMapping(value="/updateNotice", method=RequestMethod.POST)
	public String updateNotice(Notice notice, MultipartFile file){
		//Notice notice = noticeDao.selectNotice(bno);
		notice.setModifier(notice.getWriter());
		noticeDao.updateNotice(notice);
		return "board/notice/listNotice";
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteNotice", method=RequestMethod.POST)
	public ResponseEntity<String> deleteNotice(String bno){
		//Integer parsedIntegerBno = Integer.parseInt(bno);
		System.out.println("bno : " + bno);
		int parsedIntBno =  Integer.parseInt(bno);
		//Notice notice = noticeDao.selectNotice(bno);
		String fileName = noticeDao.selectNotice(parsedIntBno).getAtchflNm();
		log.info(fileName);
		if(fileName != null){
			String path=uploadPath;
			File tmpFile = new File(path+"/"+fileName);
			log.info("absol path : " + tmpFile.getAbsolutePath());
			log.info("name : " + tmpFile.getName());
			if(tmpFile.exists()){
				tmpFile.delete();
				log.info("file delete : " + path+fileName);
			}
		}
		noticeDao.deleteNotice(parsedIntBno);
		//return "board/notice/listNotice";
		return new ResponseEntity<String>("dd",	HttpStatus.OK);
	}
	
	String uploadFile(String originalName, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		try {
			if(!new File(uploadPath).exists()){
				new File(uploadPath).mkdir();
			}
			File target = new File(uploadPath, savedName);
			FileCopyUtils.copy(fileData, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedName;
	}
}
