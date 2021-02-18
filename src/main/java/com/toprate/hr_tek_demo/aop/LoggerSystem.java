package com.toprate.hr_tek_demo.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toprate.hr_tek_demo.model.ExceptionLog;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.ExceptionLogService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 17:45 17/02/2021
 */

@Aspect
@Component
public class LoggerSystem {
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Pointcut("execution(* com.toprate.hr_tek_demo.controller..*.*(..))")
    public void exceptionLogPoinCut() {
    }

    @AfterThrowing(pointcut = "exceptionLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        HttpSession session = httpSessionFactory.getObject();

        Optional<Users> user = (Optional<Users>) session.getAttribute("userInfo");
        String ipAddress = Optional.ofNullable(session.getAttribute("ipAddress")).orElse(StringUtils.EMPTY).toString();

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            methodName = className + "." + methodName;
            Map<String, String> rtnMap = convertMap(request.getParameterMap());
            ObjectMapper mapper = new ObjectMapper();
            String params = mapper.writeValueAsString(rtnMap);

            ExceptionLog excepLog = new ExceptionLog();
            excepLog.setExcRequestParam(params);
            excepLog.setMethod(methodName);
            excepLog.setExcName(e.getClass().getName());
            excepLog.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            if (user.isPresent()) {
                excepLog.setUserId(user.get().getUserId());
                excepLog.setUserName(user.get().getGmail());
            }
            excepLog.setUri(request.getRequestURI());
            excepLog.setIp(ipAddress);

            exceptionLogService.insert(excepLog);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}