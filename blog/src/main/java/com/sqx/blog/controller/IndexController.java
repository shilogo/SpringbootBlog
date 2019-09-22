package com.sqx.blog.controller;

import com.sqx.blog.config.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
//        int i=10/0;
//        String s=null;
//        if(s==null){
//           throw new NotFoundException("资源不存在");
//        }
        System.out.println("=======index====================");
        return "index";
    }

}
