package com.mythsman.controller;

import com.mythsman.service.UserComponent;
import com.mythsman.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
public class LogoutController {
    private static final Logger logger = Logger.getLogger(LogoutController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserComponent userComponent;

    @RequestMapping(path = {"/logout"})
    public String logout(@CookieValue("ticket") String ticket) {
        if(userComponent.getUser()==null){
            return "redirect:/login";
        }
        userService.logout(ticket);
        return "redirect:/login";
    }
}