package com.mythsman.controller;

import com.mythsman.service.UserComponent;
import com.mythsman.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Created by myths on 5/2/17.
 */
@Controller
public class SettingController{
    private static final Logger logger = Logger.getLogger(SettingController.class);
    @Autowired
    UserService userService;


    @RequestMapping(path = {"/setting"}, params = {"website", "email", "phone", "gender", "biography"}, method = {RequestMethod.POST})
    public String settingProfile(Model model,@Param("website") String website,@Param("email")String email,@Param("phone")String phone,@Param("gender")String gender,@Param("biography")String biography) {
        Map<String, String> map;
        map = userService.updateProfile(website,email,phone,gender,biography);
        if(map.get("msg").equals("Not login.")){
            return "redirect:/login";
        }

        model.addAttribute("msgprofile",map.get("msg"));
        model.addAttribute("module","profile");
        return "setting";
    }

    @RequestMapping(path = {"/setting"}, params = {"oldpasswd", "newpasswd", "reinput"}, method = {RequestMethod.POST})
    public String settingPassword(Model model,@Param("oldpasswd") String oldpasswd,@Param("newpasswd")String newpasswd,@Param("reinput") String reinput) {
        Map<String, String> map;
        map = userService.updatePassword(oldpasswd,newpasswd,reinput);

        if(map.get("msg").equals("Not login.")){
            return "redirect:/login";
        }
        model.addAttribute("msgpasswd",map.get("msg"));
        model.addAttribute("module","password");
        return "setting";
    }

    @RequestMapping(path = {"/setting"}, method = {RequestMethod.GET})
    public String settingGet(Model model) {
        model.addAttribute("module","profile");
        return "setting";
    }
}