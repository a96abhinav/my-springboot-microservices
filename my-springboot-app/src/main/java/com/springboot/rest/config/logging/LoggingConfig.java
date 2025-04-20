package com.springboot.rest.config.logging;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

import com.springboot.rest.config.logging.Loggable;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingConfig {

	@Pointcut("execution(* com.springboot.*.*.*(..))")
	public void logMessages() {

	}

	@Around("logMessages()")
	public Object logMessageAroundMethods(ProceedingJoinPoint pjp) throws Throwable {

		LogLevel logLevel = null;
		Boolean paramsFlag = false;
		Boolean resultFlag = false;

		Class<?> originClass = pjp.getTarget().getClass();
		Loggable loggableClass = originClass.getAnnotation(Loggable.class);

		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		Loggable loggableMethod = method.getAnnotation(Loggable.class);

		Logger log = Logger.getLogger(originClass);

		if (null != loggableMethod) {
			logLevel = loggableMethod.value();
			paramsFlag = loggableMethod.params();
			resultFlag = loggableMethod.result();
		} else if (null != loggableClass) {
			logLevel = loggableClass.value();
			paramsFlag = loggableClass.params();
			resultFlag = loggableClass.result();
		}

		Object[] args = pjp.getArgs();
		
		if (null != loggableClass || null != loggableMethod) {
			if (paramsFlag) {
				writeLogs(log, logLevel, originClass+" : "+method.getName() + "() Parameters : " + printArgs(args));
			}
		}

		Instant startTime = Instant.now();
		Object result = pjp.proceed(args);
		Instant endTime = Instant.now();

		if (null != loggableClass || null != loggableMethod) {
			if (resultFlag) {
				writeLogs(log, logLevel, originClass+" : "+method.getName() + "() Result : " + result);
			}
			writeLogs(log, logLevel, originClass+" : "+method.getName() + "() finished execution and takes ( "
					+ Duration.between(startTime, endTime).toMillis() + " ) millis time to execute");
		}

		return result;

	}

	private String printArgs(Object[] args) {
		String params = "";
		for(Object arg : args) {
			params+=arg.toString()+"  ";
		}
		return params;
	}

	private void writeLogs(Logger log, LogLevel logLevel, String message) {

		if (logLevel.equals(LogLevel.DEBUG)) {
			log.debug(message);
		} else if (logLevel.equals(LogLevel.INFO)) {
			log.info(message);
		} else if (logLevel.equals(LogLevel.WARN)) {
			log.warn(message);
		} else if (logLevel.equals(LogLevel.ERROR)) {
			log.error(message);
		} else if (logLevel.equals(LogLevel.TRACE)) {
			log.trace(message);
		}

	}
}
