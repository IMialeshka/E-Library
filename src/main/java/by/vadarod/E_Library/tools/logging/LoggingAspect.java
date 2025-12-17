package by.vadarod.E_Library.tools.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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

    @Before("loggingPointcutBook()")
    public void beforeControllerBook(JoinPoint joinPoint) {
        log.info("Вызов метода контрллера книг: " + joinPoint.getSignature().getName());
    }

    @After("loggingPointcutBook()")
    public void afterControllerBook(JoinPoint joinPoint) {
        log.info("Закончил метода контрллера книг: " + joinPoint.getSignature().getName());
    }
}
