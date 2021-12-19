package bside.palmtree.config;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class ParameterLoggingAspect {

	@Pointcut("@annotation(bside.palmtree.config.LoggingParam)")
	public void annotatedMethod() {
	}

	@Pointcut("@within(bside.palmtree.config.LoggingParam)")
	public void annotatedClass() {
	}

	@Around("(annotatedMethod()||annotatedClass())")
	public Object logTargetAndParam(ProceedingJoinPoint pjp) throws Throwable {
		Logger logger = LoggerFactory.getLogger(pjp.getSignature().getDeclaringType());

		String findTraceId = MDC.get("traceId");
		if (findTraceId == null || findTraceId.isEmpty()) {
			String traceId = UUID.randomUUID().toString();
			MDC.put("traceId", traceId);
		}

		logger.info("method: {}, param: {}", pjp.getSignature().getName(), pjp.getArgs());

		Object result = pjp.proceed();

		logger.info("method: {}, return: {}", pjp.getSignature().getName(), result);

		return result;
	}
}
