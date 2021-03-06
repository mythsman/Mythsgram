package com.mythsman.controller;

import com.mythsman.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = {"/login"}, method = {RequestMethod.GET})
    public String loginGet(Model model) {
        return "login";
    }

    @RequestMapping(path = {"/login"}, method = {RequestMethod.POST}, params = {"signIn", "inputName", "inputPassword"})
    public String loginSignInPost(Model model, @RequestParam("inputName") String name, @RequestParam("inputPassword") String password, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        Map<String, String> map;
        map = userService.login(name, password, rememberMe);
        model.addAllAttributes(map);
        if (map.get("msg").equals("success")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            httpServletResponse.addCookie(cookie);
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(path = {"/login"}, method = {RequestMethod.POST}, params = {"signUp", "inputName", "inputPassword"})
    public String loginSignUpPost(Model model, @RequestParam("inputName") String name, @RequestParam("inputPassword") String password, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        String rememberMe = httpServletRequest.getParameter("rememberMe");
        Map<String, String> map;
        map = userService.register(name, password, rememberMe);
        model.addAllAttributes(map);
        if (map.get("msg").equals("success")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            httpServletResponse.addCookie(cookie);
            return "redirect:/";
        }
        return "login";
    }
}