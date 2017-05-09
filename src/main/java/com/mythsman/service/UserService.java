package com.mythsman.service;

import com.mythsman.dao.LoginTicketDao;
import com.mythsman.dao.UserDao;
import com.mythsman.model.LoginTicket;
import com.mythsman.model.User;
import com.mythsman.util.JedisAdapter;
import com.mythsman.util.JedisKeys;
import com.mythsman.util.Md5;
import com.mythsman.util.WordFilterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by myths on 17-5-4.
 */
@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    UserDao userDao;

    @Autowired
    LoginTicketDao loginTicketDao;

    @Autowired
    UserComponent userComponent;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    WordFilterService wordFilterService;

    public Map<String, String> register(String name, String password, String rememberMe) {
        Map<String, String> map = new HashMap<>();
        if (name.length() >= 20) {
            map.put("msg", "Your name is too long");
            return map;
        }
        if (password.length() >= 20) {
            map.put("msg", "Your password is too long");
            return map;
        }
        if (userDao.selectByName(name) != null) {
            map.put("msg", "Your name has been used.");
            return map;
        }

        String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
        password = Md5.md5(password + salt);
        userDao.addUser(name, salt, password);

        map.put("msg", "success");
        String ticket = addLoginTicket(userDao.selectByName(name).getId(), rememberMe);
        map.put("ticket", ticket);
        return map;
    }

    public Map<String, String> login(String name, String password, String rememberMe) {
        Map<String, String> map = new HashMap<>();
        User user = userDao.selectByName(name);
        if (user == null) {
            map.put("msg", "User doesn't exist.");
            return map;
        } else if (Md5.md5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "success");
            String ticket = addLoginTicket(user.getId(), rememberMe);
            map.put("ticket", ticket);
            return map;
        } else {
            map.put("msg", "Password incorrect.");
            return map;
        }
    }

    public void logout(String ticket) {
        loginTicketDao.updateValid(ticket, 0);
    }

    public Map<String, String> updateProfile(String website, String email, String phone, String gender, String biography) {
        Map<String, String> map = new HashMap<>();
        if (website.length() > 40 || email.length() > 40 || phone.length() > 20 || (!gender.equals("male") && !gender.equals("female") && !gender.equals("unknown"))) {
            map.put("msg", "Your input is too long!");
            map.put("code", "0");
            return map;
        }
        User user = userComponent.getUser();
        if (user == null) {
            map.put("msg", "Please login first !");
            map.put("code", "0");
            return map;
        }
        user.setWebsite(website);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);

        biography=wordFilterService.filter(biography);
        user.setBiography(biography);

        userComponent.setUser(user);
        userDao.updateProfile(user);
        map.put("msg", "Update profile successfully!");
        map.put("code", "1");

        return map;
    }

    public Map<String, String> updatePassword(String oldpasswd, String newpasswd, String reinput) {
        Map<String, String> map = new HashMap<>();
        User user = userComponent.getUser();
        if (user == null) {
            map.put("msg", "Pleas login first!");
            map.put("code","0");
            return map;
        }
        if (!newpasswd.equals(reinput)) {
            map.put("msg", "Please input the new passwrods twice!");
            map.put("code","0");
            return map;
        }
        if (newpasswd.length() > 20 || newpasswd.length() < 1) {
            map.put("msg", "Your new password is not valid!");
            map.put("code","0");
            return map;
        }
        if (!Md5.md5(oldpasswd + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "Your old password is wrong!");
            map.put("code","0");
            return map;
        }
        String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
        newpasswd = Md5.md5(newpasswd + salt);
        userDao.updatePassword(user.getId(), salt, newpasswd);
        map.put("msg", "Update password successfully!");
        map.put("code","1");
        return map;
    }

    private String addLoginTicket(int uid, String rememberMe) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUid(uid);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        Date date = new Date();
        if (rememberMe != null && rememberMe.equals("on")) {
            date.setTime(date.getTime() + 1000L * 3600L * 24L * 30L);
        } else {
            date.setTime(date.getTime() + 1000L * 3600L * 24L);
        }
        loginTicket.setExpire(date);
        loginTicket.setValid(1);
        loginTicketDao.addTicket(loginTicket);

        return loginTicket.getTicket();
    }

    public List<Integer> getAllFollows(){
        String key= JedisKeys.getFollowKey(userComponent.getUser().getId());
        Set<String>set=jedisAdapter.smembers(key);
        List<Integer> list=new ArrayList<>();
        for(String uid:set){
            list.add(Integer.parseInt(uid));
        }
        return list;
    }
}
