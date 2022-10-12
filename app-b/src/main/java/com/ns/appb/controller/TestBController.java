package com.ns.appb.controller;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 宁松
 * @ClassName TestBController
 * @createTime 2022年10月11日 20:25
 */
@Controller
public class TestBController {
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/boss")
    public String boss(@RequestParam(value = "token",required = false) String token,
                        HttpSession session){

        if(!StringUtils.isEmpty(token)){
            Object tmp= redisTemplate.opsForValue().get(token);
            if(tmp!=null){
                String username = tmp.toString();
                session.setAttribute("username",username);
            }
        }

        Object username1 = session.getAttribute("username");//更新session时间
        System.out.println(username1);
        if(username1==null){
            return "redirect:http://localhost:8080/index.html?redirect_url=http://localhost:8081/boss";
        }
        return "index";

    }

    @GetMapping("/logout")
    public String logout( HttpServletResponse response,HttpSession session,
                          @CookieValue(value = "sso_token", required = false) String token){
        if(!StringUtils.isEmpty(token)){
            Cookie cookie = new Cookie("sso_token",null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            redisTemplate.delete(token);
            session.removeAttribute("username");
            return "redirect:http://localhost:8081/boss";
        }
        return "redirect:http://localhost:8081/boss";

    }
}
