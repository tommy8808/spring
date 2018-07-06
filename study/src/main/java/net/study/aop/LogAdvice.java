package net.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component //스프링에서 관리하는 bean 객체
@Aspect // 횡단관심(공통기능)을 지원하는 클래스
public class LogAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);
	
	//JoinPoint : method가 호출되는 시점
	// @Before, @After, @Around
	
	@Around("execution(* net.study.web..*Controller.*(..)) "
			+ "or execution(* net.study.dao..*Dao.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		//요청 전에 처리할 코드
		long start = System.currentTimeMillis();
		//메인 코드
		Object result = joinPoint.proceed();
		//메인 코드가 끝난 후 처리할 코드
		//핵심업무를 실행한 클래스와 method의 정보 확인
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name = "";
		if(type.indexOf("Controller") > -1){
			name = "컨트롤러 : ";
		}else if(type.indexOf("Service") > -1){
			name = "서비스 : ";
		}else if(type.indexOf("Dao") > -1){
			name = "모델 : ";
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		log.info(name + type + ": 실행시간 : " + time + "(ms)");
		
		return result;
	}
}
