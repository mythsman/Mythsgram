package com.mythsman.controller;

import com.alibaba.fastjson.JSON;
import com.mythsman.service.CommentService;
import com.mythsman.service.PostService;
import com.mythsman.service.UserComponent;
import com.mythsman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class TodoController {

    @Autowired
    UserComponent userComponent;


    @RequestMapping(path = {"/explore"})
    public String explore(Model model) {
        if (userComponent.getUser() == null) {
            return "redirect:/login";
        }
        return "explore";
    }

    @RequestMapping(path = {"/favourite"})
    public String favourite(Model model) {
        if (userComponent.getUser() == null) {
            return "redirect:/login";
        }
        return "favourite";
    }


}