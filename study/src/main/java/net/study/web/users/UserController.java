package net.study.web.users;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.study.dao.users.UserDao;
import net.study.domain.users.Authenticate;
import net.study.domain.users.User;

@Controller
@RequestMapping("/users")
public class UserController implements MessageSourceAware{
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private MessageSource messageSource;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping("/form")
	public String form(Model model){
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String createForm(@Valid User user, BindingResult bindingResult){
		log.info("user : {}", user);
		if(bindingResult.hasErrors()){
			log.info("Binding Result has error!");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for(ObjectError error : errors){
				log.info("error : {}", error.getDefaultMessage());
			}
			
			return "users/form";
		}
		userDao.create(user);
		log.info("Database : {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
	
	@RequestMapping("{userId}/form")//PathVariable -> 변수명이 같다면 생략가능
	public String updateForm(@PathVariable("userId") String userId, Model model){
		if(userId == null){
			throw new IllegalArgumentException("사용자 아이디가 필요합니다.");
		}
		
		User user = userDao.findById(userId);
		
		model.addAttribute("user", user);
		//form 화면 재사용
		return "users/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid User user, Model model, BindingResult bindingResult
			, HttpSession session){
		log.info("user : {}", user);
		if(bindingResult.hasErrors()){
			log.info("Binding Result has error!");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for(ObjectError error : errors){
				log.info("error : {}, {}", error.getObjectName(), error.getDefaultMessage());
			}
			String userId = user.getUserId();
			System.out.println("에러발견!!!!!!!!");
			return "users/"+userId+"/form";
		}
		
		Object temp = session.getAttribute("userId");
		if(temp == null){
			throw new NullPointerException();
		}
		
		User udpatedUser= user.updateTest(user, (String)temp);
		//User 클래스로 로직 이동
		/*String userId = (String)temp;
		if(!user.matchUserId(userId)){
			throw new NullPointerException();
		}*/
		
		userDao.update(udpatedUser);
		log.info("Database : {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
	
	@RequestMapping("/login/form")
	public String loginForm(Model model){
		model.addAttribute("authenticate", new Authenticate());
		return "users/login";
	}
	
	@RequestMapping("/login")
	public String login(Model model, @Valid Authenticate authenticate
			, BindingResult bindingResult, HttpSession session){
		if(bindingResult.hasErrors()){
			return "users/login";
		}
		
		User user = userDao.findById(authenticate.getUserId());
		if(user == null){
			model.addAttribute("errorMessage"
					, bindMessage("User.userId.mismatch"));
			return "users/login";
		}
		//이런식의 코드를 짜지 말고 vo가 해결하도록 위임하자
		/*if (user.getPassword().equals(authenticate.getPassword())){
		}*/
		//이렇게
		if(!user.matchPassword(authenticate)){
			model.addAttribute("errorMessage"
					, bindMessage("User.password.mismatch"));
			return "users/login";
		}
		session.setAttribute("userId", user.getUserId());
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("userId");
		return "redirect:/";
	}

	//메시지 종류별로 리턴하기(argument 추가 가능)
	public String bindMessage(String message){
		Object[] args = new String[] { null };//sample
		return messageSource.getMessage(message, args, Locale.getDefault());
	}

	
}
