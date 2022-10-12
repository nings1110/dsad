package com.ns.sso.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 宁松
 * @ClassName HelloController
 * @createTime 2022年10月11日 19:33
 */
@Controller
public class HelloController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                          @RequestParam("url") String url, HttpServletResponse response){
        //登录成功跳转,跳回之前的页面


        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            //保存登录成功的用户,例如redis
            String uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid,username,30, TimeUnit.MINUTES);
            Cookie sso_token = new Cookie("sso_token",uuid);
           response.addCookie(sso_token);
          return "redirect:" + url+"?token="+uuid;
        }
        return "login";
    }

    @GetMapping("/index.html")
    public String login( @CookieValue(value = "sso_token", required = false) String sso_token,HttpSession httpSession,
                        @RequestParam("redirect_url") String url, Model model){

        if(!StringUtils.isEmpty(sso_token)){
            return "redirect:"+url+"?token="+sso_token;
        }
        model.addAttribute("url",url);
        return "index.html";
    }

}
