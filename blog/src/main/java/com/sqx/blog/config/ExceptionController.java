package com.sqx.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义错误页面拦截
 */
@RestControllerAdvice
public class ExceptionController {

    private static final Logger logger= LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 统一拦截异常页面到error
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView exceprtion(HttpServletRequest request,Exception e)throws Exception{
        logger.error("Request URL: {},Exception: {}" ,request.getRequestURI(),e);

        //不处理404
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("url",request.getRequestURI());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }

}
