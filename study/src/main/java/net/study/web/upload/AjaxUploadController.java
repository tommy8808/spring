package net.study.web.upload;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.study.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(AjaxUploadController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
	public String uploadAjax(){
		return "/upload/uploadAjax";
	}
	
	//@ResponseBody : 서버 -> 클라이언트 (뷰가 아닌 데이터를 리턴)
	//@RequestBody : 클라이언트=>서버 (json 형식으로 전달)
	//ResponseEntity : 메시지 + http 상태 코드
	@ResponseBody
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
				HttpStatus.OK);
	}
}
