package com.mythsman.controller;

import com.mythsman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class CommentController {

    @Autowired
    UserService userService;

    @RequestMapping(path = {"/comment"},method = {RequestMethod.POST})
    @ResponseBody
    public String comment() {

        return "";
    }
}