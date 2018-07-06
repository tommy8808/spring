package net.study.web.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.study.domain.message.Message;
import net.study.service.message.MessageService;

@RestController
@RequestMapping("/message/*")
public class MessageController {
	
	private static final Logger log = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	//ResponseEntity : http status code+data 전달
	//@RequestBody : json 형식의 입력 데이터
	//@ResponseBody : json 형식의 출력 데이터
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody Message dto){
		ResponseEntity<String> entity = null;
		try {
			messageService.addMessage(dto);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
}
