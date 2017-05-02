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
    @ResponseBody
    public String index() {
        return "Hello world";
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(
            @PathVariable("groupId") int groupId,
            @PathVariable("userId") int userId,
            @RequestParam(value = "type", defaultValue = "1") int type,
            @RequestParam(value = "key", defaultValue = "1") int key
    ) {
        return String.format("groupId :%d userId :%d type :%d key :%d", groupId,userId,type, key);
    }

    @RequestMapping(path = {"/template"},method={RequestMethod.GET})
    public String template(Model model) {
        model.addAttribute("arg1","value1");
        return "home";
    }
}