package com.mythsman.controller;

import com.alibaba.fastjson.JSON;
import com.mythsman.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class IndexController {

    @Autowired
    PostService postService;

    @Autowired
    UserComponent userComponent;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @RequestMapping(path = {"index", "/"}, method = {RequestMethod.GET})
    public String index(Model model) {
        if (userComponent.getUser() == null) {
            return "redirect:/login";
        }
        Map<String, List<Map<String, Object>>> posts = postService.getPostsAndComments();
        model.addAllAttributes(posts);
        return "index";
    }

    @RequestMapping(path = {"index", "/"}, method = {RequestMethod.POST}, params = {"post_id", "comment", "commentBtn"})
    @ResponseBody
    public String addComment(@RequestParam("post_id") int post_id, @RequestParam("comment") String comment) {
        Map<String, String> map = commentService.addComment(post_id, comment);
        return JSON.toJSONString(map);
    }

    @RequestMapping(path={"index","/"},method = {RequestMethod.POST},params={"post_id","starBtn","comment"})
    @ResponseBody
    public String toggleStar(@RequestParam("post_id")int postId){
        String method= likeService.toggleLike(postId);
        Map<String,String> map=new HashMap<>();
        map.put("msg","success");
        map.put("type","star");
        map.put("method",method);
        map.put("id",String.valueOf(postId));
        return JSON.toJSONString(map);
    }
}