package com.mythsman.controller;

import com.mythsman.model.Post;
import com.mythsman.model.User;
import com.mythsman.service.UserComponent;
import com.mythsman.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    UserComponent userComponent;

    @RequestMapping(path = {"index", "/"})
    public String index(Model model) {


        return "index";
    }

    @RequestMapping(path = {"/explore"})
    public String explore(Model model) {
        return "explore";
    }

    @RequestMapping(path = {"/favourite"})
    public String favourite(Model model) {
        return "favourite";
    }

    @RequestMapping(path = {"/user"})
    public String homepage(Model model) {
        return "user";
    }

    @RequestMapping(path = {"/logout"})
    public String logout(@CookieValue("ticket")String ticket){
        userService.logout(ticket);
        return "redirect:/login";
    }
    @RequestMapping(path = {"/search"})
    public String search(Model model) {
        return "search";
    }
}