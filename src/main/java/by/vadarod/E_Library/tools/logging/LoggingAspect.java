package by.vadarod.E_Library.tools.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut(value = "execution(* by.vadarod.E_Library.book.rest.*.*(..))")
    public void loggingPointcutBook() {
    }

    @Pointcut(value = "execution(* by.vadarod.E_Library.user.rest.*.*(..))")
    public void loggingPointcutUser() {
    }


    @Before("loggingPointcutUser()")
    public void beforeControllerUser(JoinPoint joinPoint) {
        log.info("Вызов метода пользователей: " + joinPoint.getSignature().getName());
    }

    @Around("loggingPointcutBook()")
    public Object beforeControllerBook(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Вызов метода контрллера книг: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info("Закончил метода контрллера книг: " + joinPoint.getSignature().getName());
        return result;
    }

   /* @After("loggingPointcutBook()")
    public void afterControllerBook(JoinPoint joinPoint) {
        log.info("Закончил метода контрллера книг: " + joinPoint.getSignature().getName());
    }*/
}
