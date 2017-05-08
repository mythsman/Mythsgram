package com.mythsman.controller;

import com.alibaba.fastjson.JSON;
import com.mythsman.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by myths on 5/2/17.
 */
@ControllerAdvice
@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Value("${spring.http.multipart.max-file-size}")
    String maxSize;

    @RequestMapping(path = {"/post"}, method = {RequestMethod.GET})
    public String post() {
        return "post";
    }

    @RequestMapping(path = {"/post"}, method = {RequestMethod.POST}, params = {"title"})
    @ResponseBody
    public String postPicture(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        Map<String, Object> map = postService.publish(file, title);
        return JSON.toJSONString(map);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseBody
    public String fileSizeExceededHandler() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "Your picture should be smaller than " + maxSize + "!");
        return JSON.toJSONString(map);
    }
}