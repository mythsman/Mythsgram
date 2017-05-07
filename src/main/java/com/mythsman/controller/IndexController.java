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
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    UserComponent userComponent;

    @Autowired
    CommentService commentService;

    @RequestMapping(path = {"index", "/"}, method = {RequestMethod.GET})
    public String index(Model model) {
        if (userComponent.getUser() == null) {
            return "redirect:/login";
        }
        Map<String, List<Map<String, Object>>> posts = postService.getPostsAndComments();
        model.addAllAttributes(posts);
        return "index";
    }

    @RequestMapping(path = {"index", "/"}, method = {RequestMethod.POST}, params = {"postId", "comment", "commentBtn"})
    @ResponseBody
    public String addComment(@RequestParam("postId") int postId, @RequestParam("comment") String comment) {
        Map<String, String> map = commentService.addComment(postId, comment);
        return JSON.toJSONString(map);
    }

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


    @RequestMapping(path = {"/logout"})
    public String logout(@CookieValue("ticket") String ticket) {
        if(userComponent.getUser()==null){
            return "redirect:/login";
        }
        userService.logout(ticket);
        return "redirect:/login";
    }

    @RequestMapping(path = {"/search"})
    public String search(Model model) {
        if (userComponent.getUser() == null) {
            return "redirect:/login";
        }
        return "search";
    }
}