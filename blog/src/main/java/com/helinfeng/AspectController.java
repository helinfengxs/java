package com.helinfeng;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class AspectController {
    @Pointcut("execution(* com.helinfeng.web.controller.*.*(..))")
    public void log(){
    }
    @Before("log()")
    public void befoer(JoinPoint joinPoint){
        log.info("request is start");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestMes requestMes = new RequestMes(url,ip,classMethod,args);
        log.info(requestMes.toString());
        }
    @After("log()")
    public void after(){
        log.info("request is complete");
    }
    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfter(Object result){
        log.info("Result:{}",result);
    }

   private class RequestMes{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

       public RequestMes(String url, String ip, String classMethod, Object[] args) {
           this.url = url;
           this.ip = ip;
           this.classMethod = classMethod;
           this.args = args;
       }

       public RequestMes() {
       }

       public String getUrl() {
           return url;
       }

       public void setUrl(String url) {
           this.url = url;
       }

       public String getIp() {
           return ip;
       }

       public void setIp(String ip) {
           this.ip = ip;
       }

       public String getClassMethod() {
           return classMethod;
       }

       public void setClassMethod(String classMethod) {
           this.classMethod = classMethod;
       }

       public Object[] getArgs() {
           return args;
       }

       public void setArgs(Object[] args) {
           this.args = args;
       }

       @Override
       public String toString() {
           return "{" +
                   "url='" + url + '\'' +
                   ", ip='" + ip + '\'' +
                   ", classMethod='" + classMethod + '\'' +
                   ", args=" + Arrays.toString(args) +
                   '}';
       }
   }



}
