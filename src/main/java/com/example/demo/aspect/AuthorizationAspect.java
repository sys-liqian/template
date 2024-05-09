package com.example.demo.aspect;

import com.example.demo.annotation.Permission;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuthorizationAspect {

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.demo.annotation.Permission)")
    private void permission() {}


    @Before("permission()")
    public void checkAuthorization(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        String[] value = permission.roles();
        System.out.println(String.join(",",value));

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        System.out.println(token);
    }
}
