package com.ns.appa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 宁松
 * @ClassName TestAController
 * @createTime 2022年10月11日 21:10
 */

@Controller
public class TestAController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/employ")
    public String boss(@RequestParam(value = "token",required = false) String token,
                       HttpSession session){

        if(!StringUtils.isEmpty(token)){ //封装session用于其他服务认证登录
            Object tmp= redisTemplate.opsForValue().get(token);
            if(tmp!=null){
                String username = tmp.toString();
                session.setAttribute("username",username);
            }
        }
        Object username1 = session.getAttribute("username");//更新session时间
        System.out.println(username1);
        if(username1==null){
            return "redirect:http://localhost:8080/index.html?redirect_url=http://localhost:9000/employ";
        }
        return "index";

    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /*
    * 登出-----删除cookie与session 删除数据库中的token
    * */
    @GetMapping("/logout")
    public String logout( HttpServletResponse response,HttpSession session,
                         @CookieValue(value = "sso_token", required = false) String token){
        if(!StringUtils.isEmpty(token)){
            Cookie cookie = new Cookie("sso_token",null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            session.removeAttribute("username");
            redisTemplate.delete(token);
            return "redirect:http://localhost:9000/employ";
        }
        return "redirect:http://localhost:9000/employ";

    }
}
