package com.mythsman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class IndexController {

    @RequestMapping(path = {"index", "/"})
    public String index(Model model) {
        model.addAttribute("welcome", "work!");
        return "index";
    }

    @RequestMapping(path = {"/explore"})
    public String explore() {
        return "explore";
    }

    @RequestMapping(path = {"/favourite"})
    public String favourite() {
        return "favourite";
    }

    @RequestMapping(path = {"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping(path = {"/homepage"})
    public String homepage() {
        return "homepage";
    }

    @RequestMapping(path = {"/setting"})
    public String setting() {
        return "setting";
    }

    @RequestMapping(path = {"/mail"})
    public String mail() {
        return "mail";
    }

    @RequestMapping(path = {"/logout"})
    public String logout() {
        return "logout";
    }

}