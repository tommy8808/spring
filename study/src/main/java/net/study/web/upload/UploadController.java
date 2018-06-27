package net.study.web.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.study.util.UploadFileUtils;

@Controller
public class UploadController {
	
	private static final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	//업로드 폼 -> TODO:게시판으로 이관할것
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.GET)
	public String uploadForm(){
		return "upload/uploadForm";
	}
	
	//업로드 내용 처리 MultipartFile 에 업로드한 파일이 저장됨
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception{
		
		String savedName = file.getOriginalFilename();
		savedName = uploadFile(savedName, file.getBytes());
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName", savedName);
		return mav;
	}

	String uploadFile(String originalName, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
