package com.helinfeng.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler
    public ModelAndView excptionHandler(HttpServletRequest request,Exception e) throws Exception {
        System.out.println("=================="+e);
        log.error("Request URL : {},Exception :{}",request.getRequestURI(),e.getMessage());
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class )!= null){
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURI());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
