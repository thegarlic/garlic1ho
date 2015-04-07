package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.domain.BoardArticle;
import com.nhncorp.lucy.security.xss.XssFilter;

@Aspect
public class LucyAspect{
	private static final Logger LOGGER = LoggerFactory.getLogger(LucyAspect.class);

	//TODO 이 부분은 좀 더 세련되게 바꿔야한다. 
	@Pointcut("execution(* com.example.service.BoardArticleService.create(..))")
	private void profileTarget() {
		LOGGER.debug("컨트롤러 프로필 설정");
	}

	@Around("profileTarget()")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.debug("lucyAOP around method invoking!!");
		Object[] objects = joinPoint.getArgs();
		BoardArticle article = (BoardArticle) objects[0];
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		article.setTitle(filter.doFilter(article.getTitle()));
		article.setContent(filter.doFilter(article.getContent()));
		Object retVal = joinPoint.proceed();
		return retVal;
	}
}
