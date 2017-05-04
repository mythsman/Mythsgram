package com.mythsman.aspect;

import com.mythsman.service.ModelService;
import org.aopalliance.intercept.Joinpoint;
import org.apache.tomcat.util.security.MD5Encoder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Created by myths on 17-5-4.
 */
@Aspect
@Component
public class PageStateAspect {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PageStateAspect.class);
    @Autowired
    ModelService modelService;

    @Around("execution(* com.mythsman.controller.IndexController.*(..))")
    public void before(ProceedingJoinPoint proceedingJoinPoint) {


        String page=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();

        page=page.substring(1);
        int slashIdx=page.indexOf("/");
        if(slashIdx!=-1){
            page=page.substring(0,slashIdx);
        }

        for(Object arg:proceedingJoinPoint.getArgs()){
            logger.info(arg);
        }

        return;
    }
}
