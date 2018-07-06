package net.study.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(MessageAdvice.class);
	
	//MessageService로 시작하는 클래스의 모든 method 실행전에 경유하는 코드
	@Before("execution(* net.study.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp){
		log.info("핵심 업무 코드의 정보:" + jp.getSignature());
		log.info("method:" + jp.getSignature().getName());
		log.info("arguments:" + Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(* net.study.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		log.info(pjp.getSignature().getName() + ":" +(end-start));
		log.info("=========================");
		return result;
	}
}
