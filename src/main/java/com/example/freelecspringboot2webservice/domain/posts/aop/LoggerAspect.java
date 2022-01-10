package com.example.freelecspringboot2webservice.domain.posts.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
//@EnableAspectJAutoProxy  // Turn on aop
@Component
public class LoggerAspect {

    // controller 의 method
    @Pointcut("execution(* com.example.freelecspringboot2webservice.web.*.*(..))")
    private void loggerAspect() {
    }

    // 각 메서드의 경로와 이름을 로그로 출력
    @Around("loggerAspect()")
    public Object logPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        String type = "";  // 호출 method 의 유형 : controller
        String name = joinPoint.getSignature().getDeclaringTypeName();  //

        if (name.indexOf("Controller") >= 0) {
            type = "Controller";
        } else if (name.indexOf("ServiceImpl") >= 0) {
            type = "ServiceImpl";
        } else if (name.indexOf("Mapper") >= 0) {
            type = "Mapper";
        }
        log.debug(type + "\t" + name + "." + joinPoint.getSignature().getName());

        return joinPoint.proceed();
    }

}
