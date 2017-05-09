package com.mythsman.service;

import com.mythsman.dao.CommentDao;
import com.mythsman.dao.PostDao;
import com.mythsman.dao.UserDao;
import com.mythsman.model.Comment;
import com.mythsman.model.Post;
import com.mythsman.model.User;
import com.mythsman.util.WordFilterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myths on 17-5-4.
 */
@Service
public class CommentService {
    private static final Logger logger = Logger.getLogger(CommentService.class);

    @Autowired
    UserComponent userComponent;

    @Autowired
    CommentDao commentDao;

    @Autowired
    WordFilterService wordFilterService;

    public  Map<String,String> addComment(int postId,String comment){
        Map<String,String>map=new HashMap<>();
        comment=wordFilterService.filter(comment);
        map.put("id", Integer.toString(postId));
        if(comment.length()>100||comment.length()<1){
            map.put("msg","Input length error.");
            map.put("type","comment");
            map.put("code","0");
            return map;
        }
        User user=userComponent.getUser();
        commentDao.addComment(user.getId(),postId,comment);
        map.put("msg","Comment successfully!");
        map.put("type","comment");
        map.put("code","1");
        map.put("username", userComponent.getUser().getName());
        map.put("comment", comment);
        return map;
    }


}
