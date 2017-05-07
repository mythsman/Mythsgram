package com.mythsman.controller;

import com.mythsman.service.PostService;
import com.mythsman.service.UserComponent;
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
 * Created by myths on 5/6/17.
 */
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    PostService postService;

    @RequestMapping(path = {"/user"},method = {RequestMethod.GET})
    public String user(Model model) {
        model.addAllAttributes(postService.getPosts());
        return "user";
    }
}