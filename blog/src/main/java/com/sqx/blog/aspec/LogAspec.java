package com.sqx.blog.aspec;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面操作
 * @Aspect
 */
@Aspect
@Component
public class LogAspec {

    private Logger logger= LoggerFactory.getLogger(LogAspec.class);
    /**
     * 切面
     * execution():规定拦截哪一些类
     * com.sqx.blog.controller.*.*(..) :controller类下的所有方法
     */
    @Pointcut("execution(* com.sqx.blog.controller.*.*(..))")
    public void log(){

    }

    /**
     * 这个方法在切面之前执行，在所有的请求发过来返回之前都会执行这个歌方法
     */
    @Before("log()")
    public void doBefire(JoinPoint joinPoint){

        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url=request.getRequestURI();
        String ip=request.getRemoteAddr();
        String classMothod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMothod,args);
        logger.info("Request : {}",requestLog);
    }

    /**
     * 这后
     */
    @After("log()")
    public  void doAfter(){
//        logger.info("----------------after");
    }

    /**
     * 返回结果
     */
    @AfterReturning(returning = "result",pointcut = "log()")
    public  void doAfterRessult(Object result){
        logger.info("----------------doAfterRessult");
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMothod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMothod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMothod = classMothod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMothod='" + classMothod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
