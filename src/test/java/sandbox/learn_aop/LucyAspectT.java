package sandbox.learn_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.aop.LucyAspect4BoardArticle;
import com.example.domain.BoardArticle;
import com.nhncorp.lucy.security.xss.XssFilter;

@Aspect
public class LucyAspectT{
	private static final Logger LOGGER = LoggerFactory.getLogger(LucyAspect4BoardArticle.class);

	@Pointcut("execution(* sandbox.learn_aop.LucyTestService.create(..))")
	private void profileTarget() {
		LOGGER.debug("컨트롤러 프로필 설정");
	}

	@Around("profileTarget()")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.debug("around!!");
		Object[] objects = joinPoint.getArgs();
		//  조인포인트의 getArgs로 받아와서 처리하게합니다.
		BoardArticle article = (BoardArticle) objects[0];
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		article.setContent(filter.doFilter(article.getContent()));
		
		Object retVal = joinPoint.proceed();
		return retVal;
	}
}
